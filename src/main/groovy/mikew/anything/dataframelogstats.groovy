package mikew.anything

import groovy.time.TimeCategory
import groovy.sql.Sql
import groovy.transform.ToString

def START = 'START processing DF: '
def END = 'END processing DF: '

def MSG_QUERY =
'''select ipa_md_record_msg_code from ipa_tbl, md_status_record_tbl
where record_id = ipa_md_record and record_data_frame = ?'''

def COUNT_QUERY =
'''select IPE_PRODUCT, INSTANCE_ISAM_ID || '-' || INSTANCE_ISAM_SEQ IPE, count(*) TOTAL_IPAS
  from ipa_tbl, ipe_instance_tbl
  where instance_id = ipe_product
  and ipe_product in (
  SELECT ipe_product
  FROM IPA_TBL, md_status_record_tbl, itso_data_frame_tbl
  WHERE record_id = ipa_md_record
  and record_data_frame = df_id
  AND df_id = ?
) GROUP BY IPE_PRODUCT, INSTANCE_ISAM_ID || '-' || INSTANCE_ISAM_SEQ'''

def ISRN_QUERY = '''select isrn_iin || lpad(isrn_oid, 4, '0' ) || lpad(isrn_issn, 7, 0) || isrn_chd isrn from isrn_tbl, ipe_instance_tbl where instance_isrn = isrn_id and instance_id = ?'''

def MIN_SECONDS = 4

def url = 'jdbc:oracle:thin:@server:port:SID'
def user = 'user'
def pwd = 'password'

def idMap = [:]

def file = new File( 'C:\\df_stats\\arr_30092021.txt' )

def lines = file.readLines()

println "There are ${lines.size()} lines to parse"

def tooLow = []

lines.each {
    if ( it.contains( START ) ) {
        def dfId = it.substring( it.indexOf( START ) + START.length() )
        idMap.put( dfId, new DfStat( dfId: dfId, start: getDate( it ) ) )
    }
    else {
        def dfId = it.substring( it.indexOf( END ) + END.length() )

        if ( idMap.containsKey( dfId ) )
        {
            idMap[dfId].end = getDate( it )
            def difference = diff( idMap[dfId].end, idMap[dfId].start )
            idMap[dfId].duration = difference.text
            if ( difference.seconds == null || difference.seconds < MIN_SECONDS ) {
                tooLow << dfId
                idMap.remove( dfId )
            }
        }
    }
}

println "There are ${tooLow.size()} lines under $MIN_SECONDS seconds"

// remove stats with no duration, i.e. we didn't capture the start and end
def iterator = idMap.entrySet().iterator()

while ( iterator.hasNext() ) {
    if ( !iterator.next().value?.duration ) {
        iterator.remove()
    }
}

Sql db
try {
    db = Sql.newInstance( url, user, pwd, 'oracle.jdbc.OracleDriver' )
    idMap.each {
        it.value.msgCode = db.firstRow(MSG_QUERY, [it.key])?.IPA_MD_RECORD_MSG_CODE
        def ipeCount = db.firstRow(COUNT_QUERY, [it.key])
        it.value.totalIpas = ipeCount?.TOTAL_IPAS
        it.value.ipe = ipeCount?.IPE
        it.value.isrn = db.firstRow(ISRN_QUERY, [ipeCount?.IPE_PRODUCT])?.ISRN
        //if (it.value.totalIpas) {
            println it.value
        //}
    }
}
finally {
    db?.close()
}

// calculate difference between 2 dates & return map containing textual description and total seconds
def diff( endDate, startDate ) {
    def res = [:]
    use( TimeCategory ) {
        def duration = endDate - startDate
        res.text = "Minutes: ${duration.minutes} Seconds: ${duration.seconds}"
        res.seconds = duration.seconds
    }
    res
}

// get the date from the log file line
def getDate( line ) {
    def cutOff = line.indexOf( ':' ) + 1
    Date.parse( 'dd-MMM-yyyy HH:mm:ss', line.substring( cutOff, cutOff + 20 ) )
}

@ToString( includeFields = true, includeNames = false )
class DfStat {
    String dfId
    String msgCode
    String isrn
    String ipe
    Date start
    Date end
    String duration
    Integer totalIpas
}
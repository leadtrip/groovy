package mikew.anything

import groovy.sql.Sql

import static com.xlson.groovycsv.CsvParser.parseCsv

def csv = '''city,address1,address2
London,14,hammersmith
Bristol,The shire,queens road'''

Sql db
try {
    db = Sql.newInstance( 'url', 'user', 'pwd', 'yourDriver' )

    def data = parseCsv(csv)
    for( line in data ) {
        def dbRes = db.firstRow( 'select city from address where address1 = ? and address2 = ?', [line.address1, line.address2] )
        if ( line.city != dbRes.CITY ) {
            // do stuff
        }
    }

}
finally {
    db?.close()
}
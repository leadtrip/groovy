package mikew.soquestions.q73768991

import groovy.json.*
import groovy.transform.Canonical
import groovy.xml.*

def jsonTxt =
        '''{ "meta": {"api_response_datetime_utc": "2022-09-19T04:11:42.013964Z"},
   "warnings":    [
            {
         "id": "ABC23879",
         "issue_datetime_utc": "2022-03-07T19:21:31Z",
         "expires_datetime_utc": "2025-03-14T19:21:24Z",
         "title": "",
         "sub_title": "",
         "area_summary":          [
            "North Central",
            "North East",
            "Central",
            "West and South Gippsland and East Gippsland forecast districts"
         ],
         "phenomena_summary": null,
         "severity_code": ["STD"],
         "issue_type": "Update",
         "type": "WFR",
         "area_state_code": "VIC",
         "link_details": null
      },
            {
         "id": "BAC22000",
         "issue_datetime_utc": "2022-03-07T19:21:29Z",
         "expires_datetime_utc": "2025-03-14T19:21:24Z",
         "title": "Fire Weather",
         "sub_title": "Mallee, Northern Country, North Central and North East forecast districts",
         "area_summary":          [
            "Mallee",
            "Northern Country",
            "North Central and North East"
         ],
         "phenomena_summary": null,
         "severity_code": ["SEV"],
         "issue_type": "Alert",
         "type": "WFW",
         "area_state_code": "VIC",
         "link_details": null
      }
   ]
}'''

def jdbcTxt =
        '''<Results>
    <ResultSet fetchSize="0">
        <Row rowNumber="1">
            <WARNINGS_VW.PRODUCT_IDENT_NUMBER>ABC23879</WARNINGS_VW.PRODUCT_IDENT_NUMBER>
            <WARNINGS_VW.DMP_ID>34434</WARNINGS_VW.DMP_ID>
            <WARNINGS_VW.WARNING_TYPE>WFW</WARNINGS_VW.WARNING_TYPE>
            <WARNINGS_VW.GEOCODE_TYPE>aac:fire_district</WARNINGS_VW.GEOCODE_TYPE>
            <WARNINGS_VW.GEOCODE_CODE>VIC_FW008</WARNINGS_VW.GEOCODE_CODE>
            <WARNINGS_VW.AREA_SUMMARY>Mallee, Northern Country, North Central and North East</WARNINGS_VW.AREA_SUMMARY>
            <WARNINGS_VW.WARNING_AREA_TYPE>geocode</WARNINGS_VW.WARNING_AREA_TYPE>
            <WARNINGS_VW.SEARCH_POLYGON/>
        </Row>
        <Row rowNumber="2">
            <WARNINGS_VW.PRODUCT_IDENT_NUMBER>BAC23000</WARNINGS_VW.PRODUCT_IDENT_NUMBER>
            <WARNINGS_VW.DMP_ID>34439</WARNINGS_VW.DMP_ID>
            <WARNINGS_VW.WARNING_TYPE>WFR</WARNINGS_VW.WARNING_TYPE>
            <WARNINGS_VW.GEOCODE_TYPE>aac:public_district</WARNINGS_VW.GEOCODE_TYPE>
            <WARNINGS_VW.GEOCODE_CODE>VIC_PW008</WARNINGS_VW.GEOCODE_CODE>
            <WARNINGS_VW.AREA_SUMMARY>North Central, North East, Central, West and South Gippsland and East Gippsland forecast districts</WARNINGS_VW.AREA_SUMMARY>
            <WARNINGS_VW.WARNING_AREA_TYPE>geocode</WARNINGS_VW.WARNING_AREA_TYPE>
            <WARNINGS_VW.SEARCH_POLYGON/>
        </Row>
    </ResultSet>
</Results>'''



@Canonical
class Model {
    def id
    def warningType
    def geocodeType
    def geocodeCode
    def areaSummary
    def warningAreaType
    def searchPolygon


    def buildJdbcData(row) {
        row.with {
            id = it."WARNINGS_VW.PRODUCT_IDENT_NUMBER"
            warningType= it."WARNINGS_VW.WARNING_TYPE"
        }
    }

    def buildJsonData(warnings){
        id = warnings.id
        warningType = warnings.type
    }
}
//def jdbcResponse = testRunner.testCase.testSteps["JDBC Request"].testRequest.response.contentAsString


//def jsonResponse = testRunner.testCase.testSteps["List1-Request1"].testRequest.response.contentAsString



//Parsing the jdbc and build the jdbc model object list
def results = new XmlSlurper().parseText(jdbcTxt)
def jdbcDataObjects = []
results.ResultSet.Row.each { row -> jdbcDataObjects.add(new Model().buildJdbcData(row))}


//Parsing the json and build the json model object list
def arrayOfTagInfo = new JsonSlurper().parseText(jsonTxt)
def jsonDataObjects = []
arrayOfTagInfo.warnings.each { tagInfo ->
    jsonDataObjects.add(new Model().buildJsonData(tagInfo))

}

//sorting the Data before checking for equality
jdbcDataObjects.sort()
jsonDataObjects.sort()

assert jdbcDataObjects == jsonDataObjects, "Comparison of Jdbc XML data and Json data has failed"
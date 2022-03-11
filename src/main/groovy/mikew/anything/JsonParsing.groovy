package mikew.anything

import groovy.json.JsonGenerator
import groovy.json.JsonOutput
import groovy.json.JsonSlurper

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.TextStyle

def jsonStr = '''{ "products" : [
    {
        "id": 2,
        "name": "An ice sculpture",
        "price": 12.50,
        "tags": ["cold", "ice"],
        "dimensions": {
            "length": 7.0,
            "width": 12.0,
            "height": 9.5
        },
        "warehouseLocation": {
            "latitude": -78.75,
            "longitude": 20.4
        }
    },
    {
        "id": 3,
        "name": "A blue mouse",
        "price": 25.50,
        "dimensions": {
            "length": 3.1,
            "width": 1.0,
            "height": 1.0
        },
        "warehouseLocation": {
            "latitude": 54.4,
            "longitude": -32.7
        }
    }
]}'''

println JsonOutput.prettyPrint( jsonStr )

def jsonSlurper = new JsonSlurper()
def jsonParsed = jsonSlurper.parseText( jsonStr )

assert 2 == jsonParsed.products.size()
assert 2 == jsonParsed.products[0].tags.size()
assert 12.0 == jsonParsed.products[0].dimensions.width
println jsonParsed.products[0].id

assert [2, 3] == jsonParsed.products.collect {
    it.id
}

// ----------- parsing objects -----------

class Zoid {
    String xix
    Integer pfz
}

def z1 = new Zoid( xix: 'Monday', pfz: 10 )
println JsonOutput.prettyPrint( JsonOutput.toJson( z1 ) )

// create a JsonGenerator with converter to customze the output
def generator = new JsonGenerator.Options()
        .addConverter( new JsonGenerator.Converter() {
            @Override
            boolean handles(Class<?> type) {
                return Zoid.isAssignableFrom(type)
            }

            @Override
            Object convert(Object value, String key) {
                value.xix = DayOfWeek.valueOf(value.xix.toUpperCase()).plus(3).getDisplayName(TextStyle.FULL_STANDALONE, Locale.default)
                value.pfz = value.pfz*2
                value
            }
        })
        .build()

println JsonOutput.prettyPrint ( generator.toJson(z1) )

// ------------------------------------------------------------------------

// parse JSON string & modify

String newCardIsrn = 'newcardisrn'
String newExternalId = 'RC00019673839'
String jsonReqFromDb = '''{"scheme":{"abbreviation":"MT-WALRUS","link":"\\/schemes\\/9c5ce4ee-0f40-4cf7-beb0-4997620695bc"},"distributionDetails":{"locations":[{"reference":"location|Merseytravel - Fulfilment - Rail"}],"windowOfAvailability":{"start":"2022-03-10T14:06:28Z","period":"PT672H"}},"customer":{"link":"\\/customers\\/514259ae-ca30-4c4d-9528-c0bf2e191ec2"},"target":{"type":"CUSTOMER_MEDIA","reference":"633597014403808809","isITSO":true},"actions":[{"type":"ADD","variantExternalId":"catalogues\\/9ebc8d8b-f9a0-4c1b-9d75-1cecf5748c37\\/products\\/74\\/variants\\/0","attributes":{"AmountPaid":7500,"ValidityStartDTS":"2022-03-10T00:00:00Z","EXP":"2022-04-09","PartySizeAdult":1,"PartySizeChild":0}}],"externalId":"MW00019673839"}'''

def fmsReqParsed = new JsonSlurper().parseText( jsonReqFromDb )

assert fmsReqParsed.target.reference == '633597014403808809'
assert fmsReqParsed.externalId == 'MW00019673839'

fmsReqParsed.target.reference = newCardIsrn
fmsReqParsed.externalId = "RC${fmsReqParsed.externalId.substring(2)}"

assert fmsReqParsed.target.reference == newCardIsrn
assert fmsReqParsed.externalId == newExternalId

println JsonOutput.prettyPrint( JsonOutput.toJson( fmsReqParsed ) )
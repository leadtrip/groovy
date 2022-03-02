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
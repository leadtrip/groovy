package mikew

import groovy.json.JsonOutput
import groovy.json.JsonSlurper

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


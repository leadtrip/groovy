import groovy.json.JsonOutput
import groovy.json.JsonBuilder

def amendRequestExternalId = 'MW005244160285'
List<Map<String, String>> dets = [[SCHEMEEXTERNALID: '9c5ce4ee-0f40-4cf7-beb0-4997620695bc',
                                   SCHEMEABBREVIATION: 'MT-WALRUS',
                                   STARTX: '2021-11-09 09:51:51',
                                   ENDX: '2021-12-07 09:51:51',
                                   CUSTOMEREXTERNALID: '46dfe00d-a644-4d60-9d4a-ae0d6f5daf66']]
List<Map<String, String>> locs = [[REFERENCE: 'Cubic gates'], [REFERENCE: 'MT set']]
List<Map<String, String>> attrs = [['name': 'AmountPaid', 'integervalue': 490],['name': 'NumberRemainingPasses', 'integervalue': 1]]

JsonBuilder builder = new JsonBuilder()
builder {
    delegate.scheme {
        abbreviation dets[0]['SCHEMEABBREVIATION']
        link "/schemes/${dets[0]['SCHEMEEXTERNALID']}"
    }

    distributionDetails {

        locations(
                locs.collect {['reference': it['REFERENCE']] }
        )

        windowOfAvailability {
            start dets[0]['STARTX']
            period "PT672H"
        }
    }

    actions ([{
                  type 'AMEND'
                  attributes({
                      attrs.collect { anAttr ->
                          "${anAttr['name']}" anAttr['integervalue']
                      }
                  })
              }])

    externalId "RC${amendRequestExternalId.substring(2 )}"
}

String json = JsonOutput.prettyPrint(builder.toString())

println json

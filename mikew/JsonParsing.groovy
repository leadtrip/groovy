import groovy.json.JsonSlurper

def jsonStr = '''{
    "workflows1": {
        "name": "/wf_multifolder",
        "file": "release1/wf_multifolder.XML",
        "folderNames": {
            "multifolder": "{{multifolder}}",
            "agent1": "{{agentx}}"
        }
    },
    "workflows2": {
        "name": "/wf_multifolder",
        "file": "release1/wf_multifolder.XML",
        "folderNames": {
            "multifolder": "{{multifolder}}",
            "agent1": "{{agentx}}"
        }
    }
}'''

def jsonSlurper = new JsonSlurper()
def jsonParsed = jsonSlurper.parseText( jsonStr )

println jsonParsed.keySet()

for ( String key : jsonParsed.keySet() ) {
    println key

}
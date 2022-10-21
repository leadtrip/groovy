package mikew.soquestions.q74138850

import groovy.xml.MarkupBuilder
import groovy.xml.XmlSlurper
import groovy.xml.XmlUtil

def startingXmlTxt =
'''<root>
    <FirstName>John</FirstName>
    <LastName>Major</LastName>
</root>'''

def startingXml = new XmlSlurper().parseText(startingXmlTxt)

def sw = new StringWriter()
def bldr = new MarkupBuilder(sw)

bldr.root {
    startingXml.each { aRt ->
        row {
            Key(aRt.FirstName)
            Value(aRt.LastName)
        }
    }
}

println XmlUtil.serialize( new XmlSlurper().parseText( sw.toString() ) )
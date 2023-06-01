package mikew.soquestions.q74138850

import groovy.xml.MarkupBuilder
import groovy.xml.XmlSlurper
import groovy.xml.XmlUtil

/**
 Starting with the following xml we want the format below, then back again

 <root>
    <FirstName>John</FirstName>
    <LastName>Major</LastName>
 </root>

 To this

 <root>
    <row>
        <Key>FirstName</Key>
        <Value>John</Value>
    </row>
    <row>
        <Key>LastName</Key>
        <Value>Major</Value>
    </row>
 </root>
 */

def startingXmlTxt =
'''<root>
    <FirstName>John</FirstName>
    <LastName>Major</LastName>
</root>'''

def startingXml = new XmlSlurper().parseText(startingXmlTxt)

def sw = new StringWriter()
def bldr = new MarkupBuilder(sw)

bldr.root {
    startingXml.'*'.each { aRt ->
        row {
            Key(aRt.name())
            Value(aRt.text())
        }
    }
}

println XmlUtil.serialize( new XmlSlurper().parseText( sw.toString() ) )

startingXml = new XmlSlurper().parseText(sw.toString())
sw = new StringWriter()
bldr = new MarkupBuilder(sw)

bldr.root {
    startingXml.'*'.each{ aRt ->
        "${aRt.Key}"( aRt.Value )
    }
}

println XmlUtil.serialize( new XmlSlurper().parseText( sw.toString() ) )
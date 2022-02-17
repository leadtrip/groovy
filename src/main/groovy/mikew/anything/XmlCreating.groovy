package mikew.anything

import groovy.xml.MarkupBuilder
import groovy.xml.XmlSlurper
import groovy.xml.XmlUtil

def writer = new StringWriter()
def xml = new MarkupBuilder( writer )

xml.ITSO_POST_to_HOPS_File(){
    ITSO_Message_Header{
        ITSO_Message_Version( 2 )
        ITSO_Message_Class( 1 )
    }
    ITSO_Message_Frame {
        ITSO_Batch_Header {
            ITSO_Message_CRC( 'FF4E' )
            ITSO_Originator( '63359707E80018' )
            ITSO_Recipient( '63359707E80000' )
            ITSO_IBatch_Header( '07E800180000DA028E50028EC2000000D8CC0000000000002A0000000EB1132FF707F320A2A58DD936028F88' )
        }
    }
}

def message = new XmlSlurper().parseText( writer.toString() )
println XmlUtil.serialize( message )
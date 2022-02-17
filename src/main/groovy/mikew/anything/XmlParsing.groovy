package mikew.anything

import groovy.xml.XmlSlurper

// Options of parsing an XML structure

XmlSlurper xmlSlurper = new XmlSlurper()
xmlSlurper.setFeature( "http://apache.org/xml/features/disallow-doctype-decl", false )
xmlSlurper.setFeature( "http://apache.org/xml/features/nonvalidating/load-external-dtd", false )

def xmlMessage = xmlSlurper.parse( getClass().getResource( '../files/message.xml' ).file )

// GPath querying - GPath is a path expression language integrated into Groovy which allows parts of nested structured data to be identified.
println "Message class: ${xmlMessage.ITSO_Message_Header.ITSO_Message_Class}"
println "Message originator: ${xmlMessage.ITSO_Message_Frame.ITSO_Batch_Header.ITSO_Originator}"

println "Messages codes:"
xmlMessage.ITSO_Message_Frame.ITSO_Message_Body.ITSO_Data_Frame.ITSO_Message_Code.each{ println it }
println "Frame sealer-seq-seal:"
xmlMessage.ITSO_Message_Frame.ITSO_Message_Body.ITSO_Data_Frame.ITSO_DF_Trailer.each{ println "${it.ITSO_SealerID}-${it.ITSO_Seq_Num}-${it.ITSO_Seal}" }

// Traversing the tree
println "3rd frame, 1st destination: ${xmlMessage.ITSO_Message_Frame.ITSO_Message_Body.ITSO_Data_Frame[2].ITSO_Data_Block.ITSO_Destination[0]}"

// breadthFirst
def an0400Frame = xmlMessage.ITSO_Message_Frame.ITSO_Message_Body.ITSO_Data_Frame.'*'.find { node ->
    node.name() == 'ITSO_DTS'
}

assert 'A31E97' == an0400Frame.text()

// depthFirst
def messageCodes = xmlMessage.'**'.findAll{ node -> node.name() == 'ITSO_Message_Code' }*.text()
assert 4 == messageCodes.size()

// ******************************************** books.xml ********************************************

def booksXml = getClass().getResource( '../files/books.xml' ).text
def books = new XmlSlurper().parseText( booksXml )

def titles = books.'**'.findAll{ node-> node.name() == 'title' }*.text()
assert titles.size() == 4

titles = books.book.findAll{ book->
    /* You can use toInteger() over the GPathResult object */
    book.@id.toInteger() > 2
}*.title

assert titles.size() == 2
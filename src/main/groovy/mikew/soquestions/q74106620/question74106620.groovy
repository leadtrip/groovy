package mikew.soquestions.q74106620

import groovy.json.JsonOutput
import groovy.xml.XmlSlurper

def cat = new XmlSlurper().parseText('''
<catalogoue>
  <book>
    <title>Groovy</title>
    <author>Ken Barclay</author>
    <publisher>Elsevier</publisher>
    <isbn number='1111111111' />
  </book>
  <book>
    <title>Object Oriented Design</title>
    <author>John Savage</author>
    <publisher>Elsevier</publisher>
    <isbn number='2222222222' />
  </book>
  <book>
    <title>C Programming</title>
    <author>Ken Barclay</author>
    <publisher>Prentice Hall</publisher>
    <isbn number='3333333333' />
  </book>
</catalogoue>
''')

class Book {
    String title
    String author
    String publisher
}

def jsonCat = GQ{
    from b in cat.'*'
    select new Book( title: b.title, author: b.author, publisher: b.publisher )
}.toList()

println JsonOutput.prettyPrint( JsonOutput.toJson( jsonCat ) )
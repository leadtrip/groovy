import groovy.xml.XmlSlurper

def html =
        '''<div>
             <div-list-holder>
                  <list>
                       <custom-list-item>
                            list item 1
                       </custom-list-item>
                       <custom-list-item>
                            list item 2
                       </custom-list-item>
                       <custom-list-item>
                            list item 3
                       </custom-list-item>
                  </list>
             </div-list-holder>
        </div>'''

def elementCount( markup, elementName ) {
    new XmlSlurper().parseText(markup).'**'.findAll { node -> node.name() == elementName }.size()
}

assert 3 == elementCount( html, 'custom-list-item')
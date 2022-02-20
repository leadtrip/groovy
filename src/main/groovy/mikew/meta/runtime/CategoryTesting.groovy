package mikew.meta.runtime

import groovy.transform.ToString

/**
 * We can add methods to classes at runtime with a Category
 */

@ToString(includePackage = false)
class ChemicalElement {
    String name
    String symbol
    Integer atomicNumber
}

class ChemicalCategory {
    static void prettyPrint(ChemicalElement self ) {
        println "${self.name} has following properties"
        self.properties.each {
            if ( !['prettyPrint', 'class'].contains( it.key) ) {
                println "${it.key} - ${it.value}"
            }
        }
    }

    static ChemicalElement plus(ChemicalElement self, ChemicalElement other) {
        new ChemicalElement( name: "${self.name}${other.name}", symbol: "${self.symbol}${other.symbol}", atomicNumber: self.atomicNumber + other.atomicNumber )
    }
}

def c1 = new ChemicalElement(name: 'Argon', symbol: 'Ar', atomicNumber: 18 )
def c2 = new ChemicalElement(name: 'Carbon', symbol: 'C', atomicNumber: 6 )
def c3 = new ChemicalElement(name: 'Neon', symbol: 'Ne', atomicNumber: 10 )
def c4 = new ChemicalElement(name: 'Zinc', symbol: 'Zn', atomicNumber: 30 )

use( ChemicalCategory ) {
    [c1, c2, c3, c4].each { it.prettyPrint() }
}

println use( ChemicalCategory ) {
    c1 + c2
}
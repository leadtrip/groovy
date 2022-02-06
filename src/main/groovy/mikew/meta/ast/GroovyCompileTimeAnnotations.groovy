package mikew.meta.ast

import groovy.transform.*

// @ToString
def shimano105 = new Groupset( manufacturer: 'Shimano', name: '105' )
assert 'Groupset[manufacturer:Shimano, name:105]' == shimano105.toString()

@ToString(includePackage = false, includeNames = true, leftDelimiter = '[', rightDelimiter = ']')
class Groupset{
    String manufacturer
    String name
}

// ----------------------------------------------------------------------------------
// The @EqualsAndHashCode AST transformation aims at generating equals and hashCode methods for you.
// The generated hashcode follows the best practices as described in Effective Java by Josh Bloch:
def ws1 = new Wheelset( manufacturer: 'Mavic', name: 'Ksyrium' )
def ws2 = new Wheelset( manufacturer: 'Mavic', name: 'Ksyrium' )

/**
 * In Java, == means equality of primitive types or identity for objects.
 * In Groovy, == means equality in all places. For non-primitives, it translates to a.compareTo(b) == 0, when evaluating equality for Comparable objects, and a.equals(b) otherwise.
 * To check for identity (reference equality), use the is method: a.is(b). From Groovy 3, you can also use the === operator (or negated version): a === b (or c !== d).
 */
assert ws1==ws2
assert ws1.equals( ws2 )
assert ws1.hashCode() == ws2.hashCode()
assert !ws1.is(ws2)
assert !(ws1===ws2)
assert ws1!==ws2

@EqualsAndHashCode
class Wheelset{
    String manufacturer
    String name
}

// ----------------------------------------------------------------------------------
// @TupleConstructor
// groovy will create a bunch of constructors for you based on your fields, you can include, exclude etc various facets

// tuple based on property order
new Manufacturer( 'Shimano', 1921 )
// tuple based with default value for 2nd property
new Manufacturer( "Mike's bikes" )
// map based
def look = new Manufacturer( name: 'Look' )
look.yearEstablished = 1951
new Manufacturer( yearEstablished: 3000 )

@TupleConstructor
class Manufacturer{
    String name
    Integer yearEstablished
}

// ----------------------------------------------------------------------------------
// @MapConstructor - generates a map based constructor

def shimCass = new Cassette( manufacturer: 'Shimano', smallSprocketTeeth: 12, bigSprocketTeeth: 25  )
assert shimCass.smallSprocketTeeth == 12

@MapConstructor
class Cassette{
    String manufacturer
    Integer smallSprocketTeeth
    Integer bigSprocketTeeth
}

// ----------------------------------------------------------------------------------
// @Canonical - combines the @ToString, @EqualsAndHashCode and @TupleConstructor annotations

def ch1 = new Chain( 'Shimano', 'Ultegra', 11 )
def ch2 = new Chain( manufacturer: 'Shimano', name: 'Ultegra', speed: 11 )
assert ch1.toString() == 'mikew.meta.ast.Chain(Shimano, Ultegra, 11)'
assert ch1==ch2

@Canonical
class Chain{
    String manufacturer
    String name
    Integer speed
}

// ----------------------------------------------------------------------------------
// @InheritConstructors - AST transformation aims at generating constructors matching super constructors

@InheritConstructors
class HaddockException extends Exception{}

new HaddockException()
new HaddockException('swimming')
new HaddockException(new RuntimeException())
package mikew.meta.compiletime

import static groovy.test.GroovyAssert.shouldFail
import groovy.transform.*
import groovy.transform.builder.Builder

/**
 * Groovy comes with various AST transformations covering different needs
 * AST transformations can be separated into two categories:
    * global AST transformations are applied transparently, globally, as soon as they are found on compile classpath
    * local AST transformations are applied by annotating the source code with markers.
      Unlike global AST transformations, local AST transformations may support parameters.
 Groovy doesn’t ship with any global AST transformation
 */

// The following are all classed as code generation transformations

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
 * In Groovy, == means equality in all places. For non-primitives, it translates to a.compareTo(b) == 0,
 * when evaluating equality for Comparable objects, and a.equals(b) otherwise.
 * To check for identity (reference equality), use the is method: a.is(b).
 * From Groovy 3, you can also use the === operator (or negated version): a === b (or c !== d).
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
assert ch1.toString() == 'mikew.meta.compiletime.Chain(Shimano, Ultegra, 11)'
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

// ----------------------------------------------------------------------------------
// A few other types of annotations include...

// this class implements Comparable, has a compareTo method based on natural ordering of name and lives
// & has comparatorByName and comparatorByLives methods
@Sortable
class Cat{
    String name
    Integer lives
}

def cats = [
        new Cat('name': 'dave', lives: 9),
        new Cat('name': 'starsky', lives: 4),
        new Cat('name': 'bob', lives: 9),
        new Cat('name': 'pat', lives: 2)
]

assert cats.sort()*.name == ['bob', 'dave', 'pat', 'starsky']
assert cats.sort(false, Cat.comparatorByLives())*.name == ['pat', 'starsky', 'bob', 'dave']

// ----------------------------------------------------------------------------------
// Builder, there are a bunch of options avaiaable to customize this
@Builder
class Dog{
    String name
    String favouriteToy
}

def fido = Dog.builder().name('Fido').favouriteToy('squeaky fish').build()

// ----------------------------------------------------------------------------------
// NullCheck - defensive programming
@NullCheck
void mow(String mower, Integer grassLength){
    println "Mowing lawn with $mower to $grassLength cm"
}

def ex = shouldFail( IllegalArgumentException ) {
    mow('Bosch', null)
}

assert ex.message == 'grassLength cannot be null'

// ----------------------------------------------------------------------------------
// Immutable - combines a bunch of annotations e.g. ToString, EqualsAndHashCode, TupleConstructor, Final, ImmutableBase ...
// Immutable classes are useful since they are often easier to reason about and are inherently thread-safe
@Immutable
class Fish{
    String name
    String habitat
}

def cod = new Fish('cod', 'saltwater')
def fishy = shouldFail( ReadOnlyPropertyException ) {
    cod.name = 'halibut'
}

assert fishy.message.startsWith( 'Cannot set readonly property' )

// ----------------------------------------------------------------------------------
// Memoized - adds caching
@Memoized
long callToVerySlowService(int input) {
    println( "Got here with input $input" )
    Thread.sleep(100*input)
    System.nanoTime()
}

def x = callToVerySlowService(1) // returns after 100 milliseconds
def y = callToVerySlowService(1) // returns immediately
def z = callToVerySlowService(2) // returns after 200 milliseconds
assert x==y
assert x!=z

// ----------------------------------------------------------------------------------
// Singleton pattern, options available to customize
@Singleton
class MoneyService {
    String giveMeSomeMoney( String name ) { "Get a job $name" }
}

assert MoneyService.instance.giveMeSomeMoney( 'Derek' ) == 'Get a job Derek'

// ----------------------------------------------------------------------------------
// Synchronized - works in a similar way to the synchronized keyword but locks on different objects for safer concurrency
class SequentialNumberGenerator {
    def sequentialNumberList = []

    @Synchronized
    def addToSequenceList() {
        sequentialNumberList << ++sequentialNumberList.size()
    }
}

def seqNumGen = new SequentialNumberGenerator()
def t1 = Thread.start {
    50.times {
        seqNumGen.addToSequenceList()
        sleep 20
    }
}

def t2 = Thread.start {
    50.times {
        seqNumGen.addToSequenceList()
        sleep 20
    }
}

t1.join()
t2.join()

//Thread.sleep(2000)

assert (1..100) == seqNumGen.sequentialNumberList
package groovy3

import groovy.transform.Canonical

import java.util.Map.Entry
import java.util.stream.Collectors
import java.util.stream.Stream

def solarSystemPlanets =
        ['mercury': 57_910_000, 'venus': 108_200_000, 'earth': 149_600_000, 'mars': 227_940_000,
         'jupiter': 778_330_000, 'saturn': 1_429_400_000, 'uranus': 2_870_990_000, 'neptune': 4_504_000_000]

// java style array initialization
def nums = new int[] {1,2}
def strings = new String[] {'st1', 'st2'}

// standard java do/while loop support
def doCnt = 1
do {
    println doCnt
    doCnt++
}while( doCnt < 11 )

println '-----------------------------'

// enhanced java for loop with comma separated expressions
for( int i = 0, j = 10 ; i <= 10 ; i++, j-- ) {
    assert i + j == 10
}

// multi assignement in for loop
Map indexedAlphabet = [:]
for (def (String u, int v) = ['a', 1]; v < 27; u++, v++) {
    indexedAlphabet.put( v, u )
}

// Java lambda style syntax is now supported
def javaVowels = indexedAlphabet.entrySet().stream()
        .filter( e -> [1, 5, 9, 15, 21].contains(e.key) )
        .map( k -> k.value )
        .collect( Collectors.joining( "," ) )

println "Java vowels $javaVowels"

def groovyVowels = indexedAlphabet
        .findAll{  [1, 5, 9, 15, 21].contains(it.key) }
        .collect{ it.value }
        .join(',')

println "Grvy vowels $groovyVowels"

// The normal variants are supported and Groovy adds additional features such as default parameter values:
// general form
def add = (int x, int y) -> { def z = y; return x + z }
assert add(3, 4) == 7

// curly braces are optional for a single expression
def sub = (int x, int y) -> x - y
assert sub(4, 3) == 1

// parameter types are optional
def mult = (x, y) -> x * y
assert mult(3, 4) == 12

// no parentheses required for a single parameter with no type
def isEven = n -> n % 2 == 0
assert isEven(6)
assert !isEven(7)

// no arguments case
def theAnswer = () -> 42
assert theAnswer() == 42

// any statement requires braces
def checkMath = () -> { assert 1 + 1 == 2 }
checkMath()

// example showing default parameter values (no Java equivalent)
def addWithDefault = (int x, int y = 100) -> x + y
assert addWithDefault(1, 200) == 201
assert addWithDefault(1) == 101

// method references
// class::staticMethod
assert ['1', '2', '3'] ==
        Stream.of(1, 2, 3)
                .map(String::valueOf)
                .toList()

// class::instanceMethod
assert ['A', 'B', 'C'] ==
        ['a', 'b', 'c'].stream()
                .map(String::toUpperCase)
                .toList()

// instance::instanceMethod
def sizeAlphabet = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ'::length
assert sizeAlphabet() == 26

// instance::staticMethod
def hexer = 42::toHexString
assert hexer(127) == '7f'

// normal constructor
def r = Random::new
assert r().nextInt(10) in 0..9

@Canonical
class Planet {
    String name
    Long kmsFromSun

    Planet(Entry<String, Long> e) {
        name = e.key
        kmsFromSun = e.value
    }
}

def furthestFromTheSun = solarSystemPlanets.entrySet().stream()
        .map( Planet::new )
        .max( Comparator.comparing(p -> p.kmsFromSun ) )

println furthestFromTheSun
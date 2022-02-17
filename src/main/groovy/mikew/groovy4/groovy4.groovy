package mikew.groovy4

import java.util.stream.Collectors

// Groovy 4 features

// Maven coordinate change
//In Groovy 4.0, the groupId of the maven coordinates for Groovy have changed from org.codehaus.groovy to org.apache.groovy

enum Bike {
    ROAD, MOUNTAIN, E
}

// Switch expressions
// old style
static def bikeStrength(Bike b) {
    switch(b) {
        case Bike.ROAD: return 'Fast'
        case Bike.MOUNTAIN: return 'Off road'
        case Bike.E: return 'Steady'
    }
}

// new style, same as Java basically
static def bikeTyre(Bike b) {
    switch (b){
        case Bike.ROAD -> 'Slick'
        case Bike.MOUNTAIN -> 'Nobbly'
        case Bike.E -> 'Comfortable'
    }
}
def bike = Bike.ROAD
println "You need ${bikeTyre(bike)} tyres for a $bike bike and you will be able to go ${bikeStrength(bike)}"

// you can still use : syntax with multiple statements with yield keyword
def i = 0
println switch(i) {
    case 0:
        def a = 'ze'
        def b = 'ro'
        if (true) yield a + b
        else yield b + a
    case 1:
        yield 'one'
    case 2:
        yield 'two'
    default:
        throw new IllegalStateException('unknown number')
}

// exhaustiveness is not mandated by groovy unlike java, a default clause is returning null is implicitly added if not specified
// you should add one if null is not suitable e.g. for primitive types to avoid classcast exceptions or null pointers
def s = 'deux'
println switch(s) {
    case 'one' -> 1
    case 'two' -> 2
    default -> 0
}

// sealed classes are supported as per java
sealed interface Bicycle permits RoadBike, MountainBike {
    boolean getAero()
    boolean jump()
}

class RoadBike implements Bicycle{
    boolean getAero() { true }
    boolean jump() { false }
}

class MountainBike implements Bicycle {
    boolean getAero() { false }
    boolean jump() { true }
}

record Cyclist(String firstName, String surname, Integer grandTourWins = 0) {}

def mikeWood = new Cyclist( 'Mike', 'Wood' )
def chrisFroome = new Cyclist( 'Chris', 'Froome', 7 )
def eddyMerckx = new Cyclist( 'Eddie', 'Merckx', 11 )

def grandTourWinners =
    [mikeWood, chrisFroome, eddyMerckx].stream()
        .filter( c -> c.grandTourWins() > 0 )
        .map( c -> "${c.firstName()} ${c.surname()}" )
        .collect(Collectors.toList())

println "Grand tour winners $grandTourWinners"
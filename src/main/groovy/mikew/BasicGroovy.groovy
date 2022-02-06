package mikew

class Machine {
    Integer id
    String abo
}

// map based constructor automatically added as are getters and setters
def machine = new Machine(id: 1, abo: 'dd')
machine.getId()
machine.abo     // although this looks like direct field access it's not, the getAbo() method is being called
machine.@abo    // this is direct field access

// -------------------------------------------------------------------

// primitive types are always converted to wrapper type in groovy
assert 2.0 instanceof BigDecimal
assert 2 instanceof Integer
assert 2.0000f instanceof Float
assert 2l instanceof Long

// -------------------------------------------------------------------
// method return types
// although argument is dynamic in all following methods the return type changes based on how we've defined the signature

String returnString(param) {    // return type will always be coerced to String because we've specified return type
    param
}

def returnDef( param ) {    // return type will be decided at runtime now we're using def
    param
}

void returnVoid(param) {    // returns null
    param
}

assert returnString( 'string' ) instanceof String
assert returnString( 123 ) instanceof String
assert returnString( new Object() ) instanceof String

assert returnDef( 'string' ) instanceof String
assert returnDef( 123 ) instanceof Integer
assert returnDef( new Object() ) instanceof Object

assert !returnVoid('string' )

// -------------------------------------------------------------
// closures

def cl = {println 'wowsers'}
def iLikeClosure = {p1 -> println "I like $p1"}
cl()
iLikeClosure()  // calling closure that accepts argument doesn't cause error
iLikeClosure('coffee')

['peanut butter', 'cycling'].each { iLikeClosure(it) }

// -------------------------------------------------------------

// groovy truth

def str = ''         // empty string
def num = 0         // number < 1
def lst = []       // empty collection
def map = [:]   // empty map

assert !str
assert !num
assert !lst
assert !map

str+= 'str'
num++
lst+= 1
map.one = 1

assert str
assert num
assert lst
assert map

enum Status{
    ACTIVE,
    INACTIVE,
    DECEASED
    def asBoolean() {       // any class can implement asBoolean to indicate what it means to be true for that class
        this == ACTIVE
    }
}

Status.values().each {
    if (it)
        println "The only true status is $it"
    else
        println "Bad status $it"
}
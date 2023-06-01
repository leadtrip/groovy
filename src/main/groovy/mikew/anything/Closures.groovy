package mikew.anything

// define a closure
def c1 = { item -> item++ };
def c2 = { println item };
{ item -> println item };
{ a, b, c -> a + (b * c) };
{ String x, Integer y -> println "$y is $x" };

// closures as objects
def adder = { a, b -> a + b };
Closure printer = { println thing };
Closure<Boolean> isString = { thing -> thing instanceof String };

// calling a closure
def ten = adder(5, 5);                  // as a regular method
def cheese = adder.call('ch', 'eese');  // explicit call()

// parameters, type is optional, must have a name and an optional default value
def oneArg = { s -> s.toUpperCase() }
def expType = { String s -> s.toLowerCase() }
def defValue = { int a, int b = 2 -> a * b }

// a closure accepts no args must be defined with an empty arg list otherwise the implicit it could be used
def strictlyNoArgs = { -> "I got nothing to work with" }

// varargs can be used
def concat = { String... args -> args.join('') }
def concatWith = { String separator, String... args -> args.join(separator) }   // varargs arg must be last

/** a closure has 3 distinct things
 * this = the enclosing class
 * owner = the enclosing object where the object is defined, either a class or another closure
 * delegate = a 3rd party object to where methods and properties are resolved if the receiver of the message isn't defined
**/

// The meaning of this
class Enclosing {
    void run() {
        def whatIsThisObject = { getThisObject() }  // getThisObject is a method in the Closure class
        assert whatIsThisObject() == this
        def whatIsThis = { this }               // this is the same as whatIsThisObject
        assert whatIsThis() == this                             // with same result, the class Enclosing
    }
}
class EnclosedInInnerClass {
    class Inner {
        Closure cl = { this }       // a closure defined in an inner class
    }
    void run() {
        def inner = new Inner()
        assert inner.cl() == inner  // this in the closure will return the inner class, not the top-level one
    }
}
class NestedClosures {
    void run() {
        def nestedClosures = {
            def cl = { this }       // a closure defined inside another closure
            cl()
        }
        assert nestedClosures() == this                 // this references the closes outer class not the closure
    }
}

// below class defines closure in dump method, the closure references this.toString() where this is the enclosing class
// without this, the test would fail as you'd be calling the generic toString() on the object
class PersonOfInterest {
    String name
    int age
    String toString() { "$name is $age years old" }

    String dump() {
        def cl = {
            String msg = this.toString()        // call toString() on the outer class
            println msg
            msg
        }
        cl()
    }
}
def p = new PersonOfInterest(name:'Janice', age:74)
assert p.dump() == 'Janice is 74 years old'

// the owner of a closure
// The owner of a closure is very similar to the definition of this in a closure with a subtle difference:
// it will return the direct enclosing object, be it a closure or a class:

// the classes Enclosing and EnclosedInnerClass above remain the same but the NestedClosures class changes
class NestedClosuresOwner{
    void run() {
        def nestedClosures = {
            def cl = { owner }      // again a closure defined within another closure
            cl()
        }
        assert nestedClosures() == nestedClosures   // but this time the enclosing closure is the owner of the enclosed closure
    }
}

new NestedClosuresOwner().run()

// delegate
// the delegate defaults to the owner but unlike this and owner which refer to the lexical scope, delegate can be defined by the user
class EnclosingDel {
    void run() {
        def cl = { getDelegate() }  // get delegate by calling getDelegate
        def cl2 = { delegate }      // or reference property
        assert cl() == cl2()        // both the same...
        assert cl() == this         // which is the enclosing class or closure
        def enclosed = {
            { -> delegate }.call()  // for closures...
        }
        assert enclosed() == enclosed   // this is the owner
    }
}

// the delegate of a closure can be changed to any object
class Plane{
    String name
}
class Bucket{
    String name
}

def pl = new Plane(name: 'Margret')
def bk = new Bucket(name: 'Arthur')
def mp = [name: 'Nordberg']

def reverseName = { name.reverse() }    // name is not referencing a variable in the lexical scope of the closure

// the following calls work because the name property will be resolved transparently on the delegate object
reverseName.delegate = pl
assert reverseName() == 'tergraM'
reverseName.delegate = bk
assert reverseName() == 'ruhtrA'
reverseName.delegate = mp
assert reverseName() == 'grebdroN'

// delegation strategy
// Whenever, in a closure, a property is accessed without explicitly setting a receiver object, then a delegation strategy is involved:
/**
 Closure.OWNER_FIRST is the default strategy. If a property/method exists on the owner, then it will be called on the owner. If not, then the delegate is used.

 Closure.DELEGATE_FIRST reverses the logic: the delegate is used first, then the owner

 Closure.OWNER_ONLY will only resolve the property/method lookup on the owner: the delegate will be ignored.

 Closure.DELEGATE_ONLY will only resolve the property/method lookup on the delegate: the owner will be ignored.

 Closure.TO_SELF can be used by developers who need advanced meta-programming techniques
 */

class Frog{
    String name
    String species
    def fetchSpecies = { species }
}

class Lake{
    String name
}

def fr = new Frog( name: 'Barry', species: 'Bullfrog' )
def lk = new Lake( name: 'Windermere' )

def fetchSpeciesClosure = fr.fetchSpecies
fetchSpeciesClosure.delegate = fr
assert fetchSpeciesClosure() == 'Bullfrog'

fetchSpeciesClosure.delegate = lk
assert fetchSpeciesClosure() == 'Bullfrog'      // this works with the default resolution strategy of owner first which is Frog

fetchSpeciesClosure.resolveStrategy = Closure.DELEGATE_ONLY     // change the resolution strategy

try {
    fetchSpeciesClosure()       // now this fails because the delegate (Lake) doesn't have a species property
}
catch (MissingPropertyException mpe) {
    // species is not defined on Lake
}

// Functional programming
// currying, the concept of partial application, lets you set the value of one parameter of a closure & return a new closure accepting one less parameter
// left currying
def nCopies = { int n, String str -> str*n }                // closure expects 2 parameters
def twice = nCopies.curry(2)                       // curry sets first parameter to 2 & returns new closure
assert twice('bla') == 'blabla'                   // call the closure returned from currying which now only needs the one str parameter
assert twice('bla') == nCopies(2, 'bla')    // effectively the same thing

// right currying
def blah = nCopies.rcurry('bla')                // rcurry sets last parameter to bla and returns new closure
assert blah(2) == 'blabla'                     // call the closure returned from rcurry and pass single n parameter
assert blah(2) == nCopies(2, 'bla')      // compare the call to the new and original closure

// index based currying
def layers = { top, middle, bottom -> "$top and $middle and $bottom" }      // 3 parameter closure
def alwaysHam = layers.ncurry(0, 'ham')                                 // set parameter 0 to ham
assert "ham and chips and beans" == alwaysHam('chips', 'beans')
def alwaysHamAndChips = layers.ncurry(0, 'ham', 'chips')        // can also set multiple parameters
assert "ham and chips and egg" == alwaysHamAndChips('egg')

// memoization
def sum = { int x, int y  ->        // non-memoized closure
    println "sum ${x} + ${y}"
    return x + y
}
// these calls will both print sum 3 + 4
sum(3, 4)
sum(3, 4)

def sumMemoize = sum.memoize()      // memoized closure
// only the first call will print
sumMemoize(3, 4)
sumMemoize(3, 4)

def sumMemoizeTwice = sum.memoizeAtMost(3)      // caches at most 3 values, atLeast and between versions also available
sumMemoizeTwice(5,6)
sumMemoizeTwice(7,8)
sumMemoizeTwice(9,10)

sumMemoizeTwice(5,6)
sumMemoizeTwice(7,8)
sumMemoizeTwice(9,10)

// method pointer, store a reference to a method in a variable to call it later
def word = 'Cello'
def upperWordFunc = word.&toUpperCase   // store reference to method in variable
def upper = upperWordFunc()             // call like a method
assert upper == word.toUpperCase()
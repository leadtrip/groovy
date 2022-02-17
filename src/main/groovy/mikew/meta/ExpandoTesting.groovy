package mikew.meta

/**
 * An Expando is a dynamic representation of a typical Groovy bean.
 * Expandos support typical get and set style bean access but in addition
 * to this they will accept gets and sets to arbitrary properties.
 * If we try to access, a non-existing property, the Expando does not mind
 * and instead of causing an exception it will return null.
 * If we set a non-existent property, the Expando will add that property and set the value.
 * In order to create an Expando, we instantiate an object of class groovy.util.Expando.
 */


def customer = new Expando()

assert customer.properties == [:]
assert customer.id == null

assert customer.properties == [:]
customer.id = 1001
customer.firstName = "Fred"
customer.surname = "Flintstone"
customer.street = "1 Rock Road"

assert customer.id == 1001

assert customer.properties == [
        id:1001, firstName:'Fred',
        surname:'Flintstone', street:'1 Rock Road']
customer.properties.each { println it }

/**
 * A neat trick with Expandos is what happens when we store a closure in a property.
 * As we would expect, an Expando closure property is accessible in the same way as a normal property.
 * However, because it is a closure we can apply function call syntax to it to invoke the closure.
 * This has the effect of seeming to add a new method on the fly to the Expando.
 */

customer.prettyPrint = {
    println "Customer has following properties"
    customer.properties.each {
        if (it.key != 'prettyPrint')
            println " " + it.key + ": " + it.value
    }
}

customer.prettyPrint()

// -------------------------------------------------------------------------------------------
// Here we're testing a home brewed expando implementation
// We've implemented setProperty, getProperty and methodMissing on the HomeMadeExpando we can set and get properties
// and in this case call closures on the class at runtime

def TWO = 2
def YES = 'yes'

def homeMadeExpando = new HomeMadeExpando()
homeMadeExpando.helicopters = TWO
homeMadeExpando.chickenWire = YES
homeMadeExpando.double = { it * 2 }

assert homeMadeExpando.helicopters == TWO
assert homeMadeExpando.chickenWire == YES
assert homeMadeExpando.double(TWO) == TWO * TWO
assert homeMadeExpando.double('naughty') == 'naughtynaughty'

assert !homeMadeExpando.cheesy()
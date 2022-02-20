package mikew.meta.runtime

import groovy.transform.ToString

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

// -------------------------------------------------------------------------------------------
// Dynamic method naming
@ToString(includePackage = false)
class Computer {
    String manufacturer
    String model
    Integer cpuSpeedMhz
    Integer totalRamMb
    Integer hardDriveSizeGb
    Boolean isOverclocked
}

def comp = new Computer()
comp.properties.keySet().findAll{ !(it =~ /lass/)}.each {

        Computer.metaClass."idFor${it.capitalize()}" = { -> delegate
            delegate."$it".toString().toLowerCase().tr(' ', '_')
        }

        Computer.metaClass."upgrade${it.capitalize()}" = { -> delegate
            delegate."$it" *= 10
        }
}

def comp1 = new Computer( manufacturer: 'Dell',
                          model: 'SUPER MEGA 8000+',
                          cpuSpeedMhz: 3700,
                          totalRamMb: 32000,
                          hardDriveSizeGb: 1000000,
                          isOverclocked: false )

assert comp1.idForManufacturer() == 'dell'
assert comp1.idForModel() == 'super_mega_8000+'
println comp1.idForTotalRamMb()

assert comp1.cpuSpeedMhz * 10 == comp1.upgradeCpuSpeedMhz()
assert comp1.totalRamMb * 10 == comp1.upgradeTotalRamMb()
assert comp1.hardDriveSizeGb * 10 == comp1.upgradeHardDriveSizeGb()

println comp1
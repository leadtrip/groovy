package mikew.meta

/** All groovy classes implement GroovyObject which provides default implementations of:
        invokeMethod
        getProperty
        setProperty
    You also access a class's metaClass through GroovyObject
 **/

/**
    Following are example of adding properties to classes on the fly through its metaClass
    In this first case we're only adding a methodMissing method, no xxxProperty methods
 **/
class Whatever{}

Whatever.metaClass.methodMissing = { String m, args ->
    println "Whatever methodMissing called for $m with $args"
}

// now we can call a method with any name on Whatever class
w = new Whatever()
w.sit()
w.crawl(3)
w.takeOff()
w.eat('burgers', 'chips', 'beans')
// w.bone this will fail becasue we haven't implemented setProperty

//------------------------------------------------------------------------------------

// an optimized version of methodMissing that defines the stuff to be done in a closure then adds the closure to the
// metaClass, this means only the first call to a method not defined on the class at compile time will hit methodMissing
// all subsequent calls will be found on the metaclass meaning one less step to look for a method

// pattern used here is intercept, cache, invoke
class WhateverOptimized{}

WhateverOptimized.metaClass.methodMissing = { String m, args ->

    println "WhateverOptimized methodMissing for $m with $args"

    c = {
        m.length()
    }

    WhateverOptimized.metaClass."$m" = c
    c()
}

wo = new WhateverOptimized()
wo.moonBoots()
wo.moonBoots()      // moonBoots was added to the meta class in previous call so can be called directly, no need to fall through to methodMissing
wo.moonBoots()
wo.captainCaveman()
wo.captainCaveman()  // as above comment for moonBoots
wo.captainCaveman()


//------------------------------------------------------------------------------------

// java classes don't implement GroovyObject but we can still access their metaClass through the MetaClass registry in same manner
// here we add an isCheese method to the String class
String.metaClass.isCheese = {
        println "is cheese"
}

def cheddar = 'cheddar'
cheddar.isCheese()

// we add isHot method to instance of String theSun
def theSun = 'sun'
theSun.metaClass.isHot = {
    true
}

theSun.isCheese()   // this if fine, isCheese added to String class
println theSun.isHot()      // this is fine, isHot added to theSun instance

// println cheddar.isHot()     // can't do this, we only added the isHot method to theSun instance

//----------------------------------------------------------------------------------------

// example using groovy's extension system
// there's a file named org.codehaus.groovy.runtime.ExtensionModule in src/resources/META-INF/services that
// defines config that leads to a MikeGroovyExtension class that defines a static getZoneRpe method,
// the first argument to the method is the target class of the call, in this case that's the only argument,
// any arguments after this are the arguments to the method if that method accepted arguments


assert 'Easy' == TrainingZone.ONE.getZoneRpe()
assert 'Steady' == TrainingZone.THREE.getZoneRpe()
assert 'Hard' == TrainingZone.SEVEN.getZoneRpe()
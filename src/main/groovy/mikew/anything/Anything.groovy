// Create closures
def invokeClosure1 = { String name, Object args ->
    name
}
def invokeClosure2 = { String suffix, String name, Object args ->
    "${name}$suffix"
}
def invokeClosure2Curried = invokeClosure2.curry('!')
assert invokeClosure2Curried.parameterTypes as List == [String, Object]

// Test closures
assert invokeClosure1('foo', null) == 'foo'
assert invokeClosure2Curried('foo', null) == 'foo!'

// Create an object
def s = 'Hello'
assert s.toUpperCase() == 'HELLO'

// Override instance metaclass invokeMethod with a regular closure
s.metaClass.invokeMethod = invokeClosure1
assert s.toUpperCase() == 'toUpperCase'

// Override instance metaclass invokeMethod with a curried closure
s.metaClass.invokeMethod = invokeClosure2Curried
assert s.toUpperCase() == 'toUpperCase!'


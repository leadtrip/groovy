package mikew.meta.runtime

/**
 * Implement basic version of groovy's ExpandoMetaClass
 */
class HomeMadeExpando {
    protected dynamicProperties = [:]

    void setProperty( String propName, value ) {
        dynamicProperties[propName] = value
    }

    def getProperty(String propName) {
        dynamicProperties[propName]
    }

    def methodMissing(String methodName, args) {
        def prop = dynamicProperties[methodName]

        if( prop instanceof Closure ) {
            return prop(*args)
        }
        // returns null if we get here
    }
}
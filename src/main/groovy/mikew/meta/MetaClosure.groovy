package mikew.meta

/**
 * Every closure has an owner, the thing that created it, and a delegate, an object to which calls can be delegated to
 * The default strategy used by the groovy runtime when looking for an object to call a method on defined in the closure
 * is to try the owner first, then the delegate then fail if no suitable match is found
 * You can modify the default strategy to one of the following
    OWNER_FIRST         - default, delegate tried after
    DELEGATE_FIRST      - reverses above
    OWNER_ONLY          - fails if nothing found on owner, delegate isn't inspected
    DELEGATE_ONLY       - reverses above
 */
class MetaClosure {

    void append(String str ) {      // this will be called by closure in run method because of default resolve strategy
        println "Append called on class"
    }

    void run( String runStr )  {
        def cl = {
            append( 'My append has been called with ' )
            append( runStr )
        }

        def sb = new StringBuffer()
        cl.delegate = sb
        cl()    // resolve strategy left at default of owner first so StringBuffer append will not be used

        println sb
    }

    // --------------------------------------------------------------------------------------------

    void encrypt(String str) {          // this will not be called because we've changed the resolve strategy in the closure below
        println '£^"£(*"&"(HOED&*'
    }

    void obfuscate( String str ) {
        def cl = {
            encrypt( str )
        }

        def encrypter = new Encrypter()
        cl.delegate = encrypter
        cl.resolveStrategy = Closure.DELEGATE_FIRST     // resolve strategy modified
        cl()
    }


    static void main( args ) {
        def mc = new MetaClosure()
        mc.run( 'blobfish' )
        mc.obfuscate( 'aileron' )
    }
}

class Encrypter {
    void encrypt( String str ) {
        println '|||||||||||||||||||'
    }
}
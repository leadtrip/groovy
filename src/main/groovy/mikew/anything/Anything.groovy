class Tank {
    def foo = {
        println "Owner is $owner"
        def bar = { println "      Owner is " + owner }
        bar()
    }
}


def t = new Tank() ;
t.foo()


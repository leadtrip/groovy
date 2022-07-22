package mikew.anything

import groovy.transform.ToString

/**
 * Use @ to bypass the getter/setter
 */
@ToString
class Person {
    String forename
    String surname

    def getForename() {
        "+$forename+"
    }

    def getSurname() {
        "£${surname}£"
    }
}

def p1 = new Person(forename: 'Mike', surname: 'Wood')
println p1
println "${p1.forename} ${p1.surname}"
println "${p1.@forename} ${p1.@surname}"
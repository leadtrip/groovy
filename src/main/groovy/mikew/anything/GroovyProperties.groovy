package mikew.anything

import groovy.transform.ToString

import java.time.LocalDate

@ToString
class Human {
    String forename
    String surname
    LocalDate dob

    def initials() {
        forename[0]+surname[0]
    }

    def fullName() {
        forename+" ".concat(surname)
    }
}

def me = new Human()
me.forename = 'Mike'
me.setSurname( 'Wood' )
me['dob'] = LocalDate.of(1975, 4, 10)

println me['forename']
println me.getProperty( 'surname' )
println me.dob
println me['dob']['year']
println me.initials()
println me.fullName()
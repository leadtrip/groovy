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

// various ways to get a property
println me['forename']      // map based
println me.getForename()    // auto generated getter
println me.forename         // this is also using auto generated getter even though it looks like direct field access
println me.@forename        // this is direct field access

println me.getProperty( 'surname' )
println me.dob
println me['dob']['year']
println me.initials()
println me.fullName()
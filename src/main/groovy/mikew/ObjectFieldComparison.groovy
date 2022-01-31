package mikew

class Contract {
    String number
    String signDate
    Address address
    ContractPerson[] persons
}

class Address {
    String name
    String street
    String city
    String zip
}

class ContractPerson {
    String name
}

def contract1 = new Contract(
        number: "123",
        signDate: "2017-04-01",
        address: new Address(
                name: "Foobar",
                street: "Foostreet",
                city: "Frankfurt",
                zip: "60486"
        ),
        persons: [new ContractPerson(name: "Christian"), new ContractPerson(name: "Dave")]
)



def contract2 = new Contract(
        number: "1234",
        signDate: "2017-04-04",
        address: new Address(
                name: "Foobar",
                street: "Wrong Street",
                city: "Frankfurt",
                zip: "60483"
        ),
        persons: [new ContractPerson(name: "Frank"), new ContractPerson(name: 'Bill'), new ContractPerson(name: 'Jonty')]
)

compareFields( contract1, contract2 )

def compareFields( obj1, obj2, propName = null ) {
    obj1.properties.each {
        if ( it.value instanceof Object[] ) {
            def obj2Len = obj2."${it.key}".length

            it.value.eachWithIndex { collObj, idx ->
                if ( idx + 1 <= obj2Len )
                    compareFields( collObj, obj2."${it.key}"[idx], "${it.key}[${idx}]" )
            }
        }

        if ( !it.value.class.getCanonicalName().contains( 'java' ) ) {
            compareFields( it.value, obj2."${it.key}", it.key )
        }

        if ( it.value.class.getCanonicalName().contains( 'java' ) &&
                it.key != 'class' &&
                    it.value <=> obj2."${it.key}") {
            println "${propName ? "$propName." : ''}${it.key}: '${it.value}' != '" + obj2."${it.key}" + "'"
        }
    }
}

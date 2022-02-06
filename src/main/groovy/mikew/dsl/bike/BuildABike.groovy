package mikew.dsl.bike

import static mikew.dsl.bike.BikeBuilder.build

build {

    manufacturer 'Specialized'
    model 'Tarmac sport'

    groupset {
        pedals {
            manufacturer = 'shimano'
            model = '105'
        }
    }
}

package mikew.dsl.bike

import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap

import static groovy.lang.Closure.DELEGATE_ONLY
import static groovy.lang.Closure.DELEGATE_FIRST

class BikeBuilder {
    static void build( @DelegatesTo(value = BikeBuilderDsl, strategy = DELEGATE_ONLY) final Closure closure ) {
        final DslBike dslBike = new DslBike()
        BikeBuilderDsl bikeBuilderDsl = new BikeBuilderDsl(dslBike)

        closure.delegate = bikeBuilderDsl
        closure.resolveStrategy = DELEGATE_ONLY
        closure.call()

        println dslBike
    }
}

class BikeBuilderDsl {

    DslBike dslBike

    BikeBuilderDsl(DslBike bike) {
        dslBike = bike
    }

    void manufacturer(String name) { dslBike.manufacturer = name }
    void model(String name) { dslBike.model = name }

    void groupset (@DelegatesTo(value = GroupsetDsl, strategy = DELEGATE_ONLY) final Closure closure) {
        final GroupsetDsl groupsetDsl = new GroupsetDsl()

        closure.delegate = groupsetDsl
        closure.resolveStrategy = DELEGATE_ONLY
        closure.call()
    }
}

class GroupsetDsl{
    static final ConcurrentMap<String, String> pedals = [:] as ConcurrentHashMap

    void brakes( Closure closure ) {

    }

    void crankset( Closure closure ) {

    }

    void chain( Closure closure ) {

    }

    void pedals( @DelegatesTo(value = Map, strategy = DELEGATE_FIRST) final Closure closure) {
        pedals.with( closure )
        println pedals
    }
}

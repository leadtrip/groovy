package mikew.dsl.bike

import groovy.transform.Canonical
import groovy.transform.ToString

@ToString
@Canonical
class DslBike {
    String manufacturer
    String model
    GroupSet groupSet
}

@ToString
@Canonical
class GroupSet {
    Pedals pedals
    CrankSet crankSet
}

@ToString
@Canonical
class Pedals{
    String manufacturer
    String model
}

@ToString
@Canonical
class CrankSet{
    String manufacturer
    String model
    Integer speed
    Integer length
    List<Integer> chainRingSizes
}
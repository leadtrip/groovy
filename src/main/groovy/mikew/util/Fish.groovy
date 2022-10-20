package mikew.util

import groovy.transform.Canonical

import java.time.LocalDate

@Canonical
class Fish {
    String first, type, bodyOfWater
    LocalDate dob
}

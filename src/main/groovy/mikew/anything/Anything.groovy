package mikew.anything

import java.time.Clock
import java.time.LocalDate
import java.time.Year
import java.time.temporal.ChronoField

println Clock.systemDefaultZone()
println LocalDate.now(Clock.systemDefaultZone()).get( ChronoField.YEAR )
println Year.now(Clock.systemDefaultZone())

println new Date().toYear()
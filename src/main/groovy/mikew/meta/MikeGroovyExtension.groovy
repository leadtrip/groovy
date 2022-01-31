package mikew.meta

class MikeGroovyExtension {

    /**
     * @param zone  - training zone
     * @return a very simple textual Rate of Perceived Exertion
     */
    static String getZoneRpe( TrainingZone zone ) {
        def maxThreshold = zone.pcThreshold.getTo()
        return maxThreshold < 60 ? 'Easy' : maxThreshold < 91 ? 'Steady' : 'Hard'
    }
}

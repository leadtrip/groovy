def input = new File( "../resources/day7input.txt" ).eachLine{it.split ( ',' ) }.collect { it.toInteger()}

def rangeFuelMap = [:]
def max = input.max()
(1..max).each { aRange ->
    def totalForRange = 0
    input.each { horiz ->
        totalForRange+= Math.abs( aRange - horiz )
    }
    rangeFuelMap.put( aRange, totalForRange )
}

println rangeFuelMap.min { it.value }
def input = new File( "../resources/day7input.txt" ).eachLine{it.split ( ',' ) }.collect { it.toInteger()}

def rangeFuelMap = [:]
def max = input.max()
(0..max).each { aRange ->
    def totalForRange = 0
    input.each { horiz ->
        def gap = Math.abs( aRange - horiz )
        totalForRange+= (0..gap).sum()
    }
    rangeFuelMap.put( aRange, totalForRange )
}

// first answer of 96086266 too high

// initially had (1..gap), should have been (0..gap)
// 478=96086265

println rangeFuelMap.min { it.value }
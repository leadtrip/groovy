def input = new File( "../resources/day6input.txt" ).eachLine{it.split ( ',' ) }.collect { it.toInteger()}

def bigInput = []
bigInput.addAll( input )

def idx = 0
80.times {
    bigInput.eachWithIndex { timer, timerIdx ->
        if (input[timerIdx] == 0) {
            input[timerIdx] = 6
            input[input.size()] = 8
        } else {
            input[timerIdx]--
        }
    }
    bigInput.clear()
    bigInput.addAll( input )
    println idx++
}

println bigInput.size()


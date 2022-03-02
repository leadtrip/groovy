def horiz = 0
def depth = 0
def aim = 0

new File( "../resources/day2input.txt" ).text.eachLine {
    def inputLine = it.split( '\\s' )
    def command = inputLine[0]
    def value = inputLine[1].toInteger()

    switch ( command ) {
        case 'forward':
            horiz += value
            depth += aim * value
            break
        case 'down':
            aim += value
            break
        case 'up' :
            aim -= value
    }
}

println horiz * depth

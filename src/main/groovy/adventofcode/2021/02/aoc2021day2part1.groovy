def horiz = 0
def depth = 0

new File( "../resources/day2input.txt" ).text.eachLine {
    def inputLine = it.split( '\\s' )

    switch ( inputLine[0] ) {
        case 'forward':
            horiz += inputLine[1].toInteger()
            break
        case 'down':
            depth += inputLine[1].toInteger()
            break
        case 'up' :
            depth -= inputLine[1].toInteger()
    }
}

println horiz * depth



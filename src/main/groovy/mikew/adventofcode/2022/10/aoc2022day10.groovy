def input = new File( "../resources/day10input.txt" ).collect{it}

def testInput =
        ['addx 15',
         'addx -11',
         'addx 6',
         'addx -3',
         'addx 5',
         'addx -1',
         'addx -8',
         'addx 13',
         'addx 4',
         'noop',
         'addx -1',
         'addx 5',
         'addx -1',
         'addx 5',
         'addx -1',
         'addx 5',
         'addx -1',
         'addx 5',
         'addx -1',
         'addx -35',
         'addx 1',
         'addx 24',
         'addx -19',
         'addx 1',
         'addx 16',
         'addx -11',
         'noop',
         'noop',
         'addx 21',
         'addx -15',
         'noop',
         'noop',
         'addx -3',
         'addx 9',
         'addx 1',
         'addx -3',
         'addx 8',
         'addx 1',
         'addx 5',
         'noop',
         'noop',
         'noop',
         'noop',
         'noop',
         'addx -36',
         'noop',
         'addx 1',
         'addx 7',
         'noop',
         'noop',
         'noop',
         'addx 2',
         'addx 6',
         'noop',
         'noop',
         'noop',
         'noop',
         'noop',
         'addx 1',
         'noop',
         'noop',
         'addx 7',
         'addx 1',
         'noop',
         'addx -13',
         'addx 13',
         'addx 7',
         'noop',
         'addx 1',
         'addx -33',
         'noop',
         'noop',
         'noop',
         'addx 2',
         'noop',
         'noop',
         'noop',
         'addx 8',
         'noop',
         'addx -1',
         'addx 2',
         'addx 1',
         'noop',
         'addx 17',
         'addx -9',
         'addx 1',
         'addx 1',
         'addx -3',
         'addx 11',
         'noop',
         'noop',
         'addx 1',
         'noop',
         'addx 1',
         'noop',
         'noop',
         'addx -13',
         'addx -19',
         'addx 1',
         'addx 3',
         'addx 26',
         'addx -30',
         'addx 12',
         'addx -1',
         'addx 3',
         'addx 1',
         'noop',
         'noop',
         'noop',
         'addx -9',
         'addx 18',
         'addx 1',
         'addx 2',
         'noop',
         'noop',
         'addx 9',
         'noop',
         'noop',
         'noop',
         'addx -1',
         'addx 2',
         'addx -37',
         'addx 1',
         'addx 3',
         'noop',
         'addx 15',
         'addx -21',
         'addx 22',
         'addx -6',
         'addx 1',
         'noop',
         'addx 2',
         'addx 1',
         'noop',
         'addx -10',
         'noop',
         'noop',
         'addx 20',
         'addx 1',
         'addx 2',
         'addx 2',
         'addx -6',
         'addx -11',
         'noop',
         'noop',
         'noop']

// this was fine until final 220 check
/*def x = 1
def toProcess = new ArrayDeque()

for( String aLine : testInput ) {
    if ( aLine.trim().startsWith( 'add') ) {
        toProcess.offerLast(0)
        toProcess.offerLast( aLine.split( ' ' )[1] as Integer )
    }
    else {
        toProcess.offerLast(0)
    }
}*/

def myx = 1
def mycycle = 1
def mytotal = 0

for( String aLine : input ) {
    if ( mycycle % 40 == 20 ) {
        mytotal += mycycle * myx
    }
    mycycle++

    if ( aLine.trim().startsWith( 'add') ) {
        if ( mycycle % 40 == 20 ) {
            mytotal += mycycle * myx
        }
        def add = aLine.split( ' ' )[1] as Integer
        myx += add
        mycycle++
    }
}

println "Part 1: $mytotal"
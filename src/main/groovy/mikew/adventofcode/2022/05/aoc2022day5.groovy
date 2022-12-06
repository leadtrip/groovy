def input = new File( "../resources/day5input.txt" ).collect {it}

Deque<String> col1 = new ArrayDeque<>();
col1.offerLast("S")
col1.offerLast("L")
col1.offerLast("F")
col1.offerLast("Z")
col1.offerLast("D")
col1.offerLast("B")
col1.offerLast("R")
col1.offerLast("H")

Deque<String> col2 = new ArrayDeque<>();
col2.offerLast("R")
col2.offerLast("Z")
col2.offerLast("M")
col2.offerLast("B")
col2.offerLast("T")

Deque<String> col3 = new ArrayDeque<>();
col3.offerLast("S")
col3.offerLast("N")
col3.offerLast("H")
col3.offerLast("C")
col3.offerLast("L")
col3.offerLast("Z")

Deque<String> col4 = new ArrayDeque<>();
col4.offerLast("J")
col4.offerLast("F")
col4.offerLast("C")
col4.offerLast("S")

Deque<String> col5 = new ArrayDeque<>();
col5.offerLast("B")
col5.offerLast("Z")
col5.offerLast("R")
col5.offerLast("W")
col5.offerLast("H")
col5.offerLast("G")
col5.offerLast("P")

Deque<String> col6 = new ArrayDeque<>();
col6.offerLast("T")
col6.offerLast("M")
col6.offerLast("N")
col6.offerLast("D")
col6.offerLast("G")
col6.offerLast("Z")
col6.offerLast("J")
col6.offerLast("V")

Deque<String> col7 = new ArrayDeque<>();
col7.offerLast("Q")
col7.offerLast("P")
col7.offerLast("S")
col7.offerLast("F")
col7.offerLast("W")
col7.offerLast("N")
col7.offerLast("L")
col7.offerLast("G")

Deque<String> col8 = new ArrayDeque<>();
col8.offerLast("R")
col8.offerLast("Z")
col8.offerLast("M")

Deque<String> col9 = new ArrayDeque<>();
col9.offerLast("T")
col9.offerLast("R")
col9.offerLast("V")
col9.offerLast("G")
col9.offerLast("L")
col9.offerLast("C")
col9.offerLast("M")

// 2 minutes in notepad++ with a macro saves parsing all that nonsense above
def arrDeq = [col1, col2, col3, col4, col5, col6, col7, col8, col9]

input.each {aLine ->
    if ( aLine.startsWith('move') ) {
        printIt(arrDeq)
        def numbers = aLine.replaceAll("\\s", "").split('move|from|to').collect{ if( it ) it.toInteger()}

        def from = numbers[2] - 1
        def to = numbers[3] - 1
        def toMove = numbers[1]

        // Comment/uncomment Part 1 or 2 below to get answer
        // Part 1
/*        toMove.times {
            arrDeq[to].offerFirst(arrDeq[from].pollFirst())
        }*/

        // Part 2
        def topN = []
        toMove.times {
            topN << arrDeq[from].pollFirst()
        }
        topN.reverse().each {
            arrDeq[to].offerFirst(it)
        }
    }
}

solution(arrDeq)

def printIt( arrDeq ) {
    println '--------------------------'
    arrDeq.each{
        println it
    }
}

def solution( arrDeq ) {
    print 'Solution: '
    arrDeq.each{
        print it.peekFirst()
    }
}



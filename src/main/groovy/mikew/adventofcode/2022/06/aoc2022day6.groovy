def input = new File( "../resources/day6input.txt" ).text

def testInput = 'nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg'

def charDeq = new ArrayDeque()
def idx = 0

// ---- Part 1 ----
input.find { aChar ->
    charDeq.offerFirst(aChar)
    if ( charDeq.size() >= 4 ) {
        def cB = charDeq.countBy {it}
        if ( cB.size() == 4 ) {
            println "First unique char: (" + charDeq.peekFirst() + ') [' + (idx+1) + ']'
            return idx
        }
        charDeq.pollLast()
    }
    idx++
    false
}

// ---- Part 2 ----
idx = 0
charDeq.clear()
input.find { aChar ->
    charDeq.offerFirst(aChar)
    if ( charDeq.size() >= 14 ) {
        def cB = charDeq.countBy {it}
        if ( cB.size() == 14 ) {
            println "First unique char: (" + charDeq.peekFirst() + ') [' + (idx+1) + ']'
            return idx
        }
        charDeq.pollLast()
    }
    idx++
    false
}
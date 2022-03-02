def input = new File( "../resources/day4input.txt" ).readLines()

def randomNumbers = input[0].split(',') as List<Integer>

def boardList = []
def currentBoard = [:]
def newBoardIndex = 0
input.eachWithIndex{ it, idx ->
    if ( idx > 0 && it.isBlank() ) {
        currentBoard = [:]
        newBoardIndex = 0
    }
    else {
        currentBoard.put( newBoardIndex, it.trim().split( '\\s+' ) as List<Integer> )
        newBoardIndex++
        if ( newBoardIndex == 5 ) {
            boardList << currentBoard
        }
    }
}

def winningBoards = []
def winningValues = []
randomNumbers.each {randomNumber ->
    boardList.eachWithIndex { aBoard, idx ->
        if (!winningBoards.contains(idx)) {
            aBoard.eachWithIndex { aBoardMap, aBoardMapIdx ->
                def found = aBoardMap.value.findIndexOf { it == randomNumber }
                if (found > -1) {
                    aBoardMap.value[found] = -1
                    if (checkBoard(aBoard)) {
                        println 'board ' + ++idx.toInteger() + ' wins'
                        def rem = aBoard.values().flatten()
                        rem.removeAll { it.toInteger() < 0 }
                        winningValues << rem.sum { it.toInteger() } * randomNumber.toInteger()
                        winningBoards << idx
                    }
                }
            }
        }
    }
}

// solution
// part 1 = 21607
// part 2 = 19012
println 'first winning board: ' + winningValues.first()
println 'last winning board: ' + winningValues.last()

static def checkBoard(aBoard ) {
    checkHorizontal( aBoard ) || checkVertical( aBoard )
}

static def checkHorizontal( aBoard ) {
    def bingo = false
    aBoard.each{ aRow ->
        def tot = 0
        aRow.value.each{
            tot += it.toInteger()
        }
        if (tot < 0) {
            bingo = true
        }
    }
    bingo
}

static def checkVertical( aBoard ) {
    def colTotals = [0,0,0,0,0]
    aBoard.each { aRow ->
        aRow.value.eachWithIndex{ val, idx ->
            def toAdd = val.toInteger() == -1 ? val.toInteger() : 0
            colTotals[idx] += toAdd
        }
    }
    colTotals.any{ it == -5 }
}
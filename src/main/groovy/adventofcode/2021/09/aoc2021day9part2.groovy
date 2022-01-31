def inputList = []
new File( "../resources/day9input.txt" ).eachLine{inputList << it.split ( '' )  }
def total = 0
inputList.eachWithIndex { aLine, lineIdx ->
    aLine.eachWithIndex{ aHeight, heightIdx ->
        def current = aHeight.toInteger()
        def above = ( lineIdx == 0  ? 10 : inputList[lineIdx-1][heightIdx] ).toInteger()
        def right = ( heightIdx == aLine.size() -1 ? 10 : aLine[heightIdx+1] ).toInteger()
        def below = ( lineIdx == inputList.size()-1 ? 10 : inputList[lineIdx+1][heightIdx] ).toInteger()
        def left = ( heightIdx == 0 ? 10 : aLine[heightIdx-1] ).toInteger()

        if( current < [above, right, below, left].min() ) {
            //total+= current + 1
            checkBasin( inputList, lineIdx, heightIdx )
        }
    }
}

def checkBasin( inputList, lineIdx, heightIdx ) {
    println lineIdx + ' ' + heightIdx
}

//println total
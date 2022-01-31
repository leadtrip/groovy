def input = new File( "../resources/day3input.txt" ).readLines()

assert 2981085 == reduce( input, 0 ) * reduce( input, 0, false )

def reduce ( binaryList, bitPos, mostCommon = true ) {
    def zeroList = []
    def oneList = []
    binaryList.each {
        it[bitPos].toInteger() == 0 ? zeroList << it : oneList << it
    }
    def next = nextInput( zeroList, oneList, mostCommon )
    if ( next.size() == 1 )
        return Integer.parseInt( next[0], 2 )
    else
        bitPos++
        reduce( next, bitPos, mostCommon )
}

static nextInput( zeroList, oneList, mostCommon ) {
    if ( mostCommon ) {
        return oneList.size() == zeroList.size() ? oneList : oneList.size() > zeroList.size() ? oneList : zeroList
    }
    oneList.size() == zeroList.size() ? zeroList : zeroList.size() < oneList.size()  ? zeroList : oneList
}
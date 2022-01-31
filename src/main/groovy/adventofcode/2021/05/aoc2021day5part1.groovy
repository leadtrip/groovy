import java.util.regex.Pattern

/*
for full input as...
0,9 -> 5,9
8,0 -> 0,8
9,4 -> 3,4
2,2 -> 2,1
7,0 -> 7,4
6,4 -> 2,0
0,9 -> 2,9
3,4 -> 1,4
0,0 -> 8,8
5,5 -> 8,2

only want where x=x or y=y so end up with

0,9 -> 5,9
9,4 -> 3,4
2,2 -> 2,1
7,0 -> 7,4
0,9 -> 2,9
3,4 -> 1,4
 */

def mdArr = new int[1000][1000]

new File( "../resources/day5input.txt" ).eachLine {
    def aLine = Pattern.compile(",| -> ").split(it, 4)
    def firstX = aLine[0].toInteger()
    def secondX = aLine[2].toInteger()
    def firstY = aLine[1].toInteger()
    def secondY = aLine[3].toInteger()
    if ( firstX == secondX || firstY == secondY ) {
        def xMatch = firstX == secondX
        if ( xMatch ) {
            (firstY..secondY).each {
                mdArr[firstX][it]++
            }
        }
        else {
            (firstX..secondX).each {
                mdArr[it][firstY]++
            }
        }
    }
}

def overlap = []
mdArr.each{
    overlap.addAll( it.findAll { it > 1 } )
}

assert 4993 == overlap.size()
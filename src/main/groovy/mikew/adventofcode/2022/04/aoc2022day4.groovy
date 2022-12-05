def input = new File( "../resources/day4input.txt" ).collect {it}

def testInput = [
                             '2-4,6-8',
                             '2-3,4-5',
                             '5-7,7-9',
                             '2-8,3-7',
                             '6-6,4-6',
                             '2-6,4-8'
                                ]

def totalFullyContains = 0
def totalOverlap = 0
input.each {aLine->
    def bothParts = aLine.split(',')

    def leftNums = bothParts[0].split('-').collect {Integer.parseInt(it)}
    def leftRange = ( leftNums[0] .. leftNums[1]  )

    def rightNums = bothParts[1].split('-').collect {Integer.parseInt(it)}
    def rightRange = ( rightNums[0] .. rightNums[1]  )

    totalFullyContains += leftRange.containsAll(rightRange) ? 1 : rightRange.containsAll(leftRange) ? 1 : 0

    totalOverlap += leftRange.contains(rightRange.first()) ? 1 : leftRange.contains(rightRange.last()) ? 1 :
                        rightRange.contains(leftRange.first()) ? 1 : rightRange.contains(leftRange.last()) ? 1 : 0
}

println totalFullyContains  // part 1
println totalOverlap        // part 2
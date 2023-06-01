def input = new File( "../resources/day8input.txt" ).text

def outer = 392

def getRowsAndCols() { 99 }

List<Integer> matrix = []
input.eachLine{ aLine ->
	matrix << aLine.split("(?!^)").collect { it as Integer }
}

def part1Total = outer
def part2Total = 0

def treeScore = { row, th ->
	def tot = row.findIndexOf{ it >= th }
	tot == -1 ? row.size() : tot + 1
}

def visibleTrees = { row, th ->
	row.findAll( aTree -> aTree >= th )
}

matrix.eachWithIndex { aRow, rowIdx ->
	if ( rowIdx > 0 && rowIdx < rowsAndCols-1 ) {
		aRow.eachWithIndex { treeHeight, valIdx ->
			if ( valIdx > 0 && valIdx < rowsAndCols-1 ) {

				def left = matrix[rowIdx].subList(0, valIdx)
				def down = matrix[rowIdx].subList(valIdx+1, rowsAndCols)
				def right = (rowIdx+1.. rowsAndCols-1).collect {matrix[it][valIdx] }
				def up = (rowIdx-1.. 0).collect {matrix[it][valIdx] }

				if (!visibleTrees( left, treeHeight ) ||
						!visibleTrees( up, treeHeight ) ||
							!visibleTrees( right, treeHeight ) ||
								!visibleTrees( down, treeHeight ) ) {
					part1Total++
				}

				def newPart2 =
					treeScore( left.reverse(), treeHeight ) *
						treeScore( up, treeHeight ) *
							treeScore( right, treeHeight ) *
								treeScore( down, treeHeight )

				part2Total = Integer.max( newPart2, part2Total )
			}
		}
	}
}

println "Part 1 total: $part1Total"		// Part 1 = 1688
println "Part 2 total: $part2Total"		// Part 2 = 410400

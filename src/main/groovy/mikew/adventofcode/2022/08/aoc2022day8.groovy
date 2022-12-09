def input = new File( "../resources/day8input.txt" ).text

def testInput =
				'30373\n' +
				'25512\n' +
				'65332\n' +
				'33549\n' +
				'35390'

//def outer = 16
def outer = 392

//def rowsAndCols = 5
def getRowsAndCols() { 99 }

List<Integer> matrix = []

def idx = 1
input.eachLine{ aLine ->
	matrix << aLine.split("(?!^)").collect { it as Integer }
	idx++
}

def total = outer

matrix.eachWithIndex { aRow, rowIdx ->
	if ( rowIdx > 0 && rowIdx < rowsAndCols-1 ) {
		aRow.eachWithIndex { aVal, valIdx ->
			if ( valIdx > 0 && valIdx < rowsAndCols-1 ) {
				//println "--- Row $rowIdx Col $valIdx Val $aVal ----"
				if ( !check( aVal as Integer, 'left', matrix, rowIdx, valIdx ) ||
						!check( aVal as Integer, 'right', matrix, rowIdx, valIdx ) ||
							!check( aVal as Integer, 'below', matrix, rowIdx, valIdx ) ||
								!check( aVal as Integer, 'above', matrix, rowIdx, valIdx ) ) {
					total++
				}
			}
		}
	}
}

println "Total: $total"		// 1688

def check( Integer treeHeight, String where, List<Integer> matrix, Integer rowIdx, Integer startingCol ) {
	if( treeHeight == 0 ) {
		return 100
	}
	switch (where) {
		case 'left':
			return matrix[rowIdx].subList(0, startingCol).find{ it >= treeHeight }
		case 'below':
			Integer max = rowsAndCols-1
			def range = new NumberRange(rowIdx+1, max)
			def allBelow = []
			range.each {
				allBelow << matrix[it][startingCol]
			}
			return allBelow.find { it >= treeHeight }
		case 'right':
			return matrix[rowIdx].subList(startingCol+1, rowsAndCols).find{ it >= treeHeight }
		case 'above':

			def range = new NumberRange(rowIdx-1, 0)
			def allAbove = []
			range.each {
				allAbove << matrix[it][startingCol]
			}
			return allAbove.find { it >= treeHeight }
	}
}
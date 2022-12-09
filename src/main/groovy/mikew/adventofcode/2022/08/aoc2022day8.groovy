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
input.eachLine{ aLine ->
	matrix << aLine.split("(?!^)").collect { it as Integer }
}

def total = outer

matrix.eachWithIndex { aRow, rowIdx ->
	if ( rowIdx > 0 && rowIdx < rowsAndCols-1 ) {
		aRow.eachWithIndex { aVal, valIdx ->
			if ( valIdx > 0 && valIdx < rowsAndCols-1 ) {
				//println "--- Row $rowIdx Col $valIdx Val $aVal ----"
				if ( !check( aVal , 'left', matrix, rowIdx, valIdx ) ||
						!check( aVal, 'right', matrix, rowIdx, valIdx ) ||
							!check( aVal, 'below', matrix, rowIdx, valIdx ) ||
								!check( aVal, 'above', matrix, rowIdx, valIdx ) ) {
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
			return (rowIdx+1.. rowsAndCols-1).collect {matrix[it][startingCol] }.find { it >= treeHeight }
		case 'right':
			return matrix[rowIdx].subList(startingCol+1, rowsAndCols).find{ it >= treeHeight }
		case 'above':
			return (rowIdx-1.. 0).collect {matrix[it][startingCol] }.find { it >= treeHeight }
	}
}
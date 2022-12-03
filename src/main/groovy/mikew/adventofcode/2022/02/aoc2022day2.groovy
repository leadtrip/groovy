def input = new File( "../resources/day2input.txt" ).collect {it}

def pairList = ['0 0', 'B X', 'C Y', 'A Z', 'A X', 'B Y', 'C Z', 'C X', 'A Y', 'B Z']

println input.inject( 0 ) { acc, val -> pairList.indexOf(val) + acc }
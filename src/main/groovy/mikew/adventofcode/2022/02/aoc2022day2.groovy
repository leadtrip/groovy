def input = new File( "../resources/day2input.txt" ).collect {it}

def firstHalf = ['0 0', 'B X', 'C Y', 'A Z', 'A X', 'B Y', 'C Z', 'C X', 'A Y', 'B Z']
def secondHalf = ['0 0', 'B X', 'C X', 'A X', 'A Y', 'B Y', 'C Y', 'C Z', 'A Z', 'B Z']

println input.inject( 0 ) { acc, val -> firstHalf.indexOf(val) + acc }

println input.inject( 0 ) { acc, val -> secondHalf.indexOf(val) + acc }
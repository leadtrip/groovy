def input = new File( "../resources/day3input.txt" ).readLines()
def totMap = [0:0,1:0,2:0,3:0,4:0,5:0,6:0,7:0,8:0,9:0,10:0,11:0]
input.each {
    it.eachWithIndex { bit, idx ->
        ( bit.toInteger() > 0 ) ? totMap[idx]++ : totMap[idx]--
    }
}

def gamma =  Integer.parseInt( totMap.values().collect {it > 0 ? 1 : 0}.join(), 2 )

def epsilon = gamma^0b111111111111

println gamma * epsilon
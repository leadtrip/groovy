def input = new File( "../resources/day1input.txt" ).collect {it}

def totalList = []

def blockTotal = 0
for (String s : input) {
    try{
        blockTotal += Integer.parseInt(s)
    }
    catch (NumberFormatException ignored) {
        totalList << blockTotal
        blockTotal = 0
    }
}
println totalList.max()
println totalList.sort().reverse().take(3).sum()







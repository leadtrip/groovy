def pairs = ["(": ")", "{": "}", "[": "]", "<": ">"]
def points = [")": 3, "]": 57, "}": 1197, ">": 25137]
def p2Points = [")": 1, "]": 2, "}": 3, ">": 4]

def pt1Points = 0
def okay = []

for( String line : new File( "../resources/day10input.txt" ).readLines() ) {
    Stack<String> stack = new Stack<>();
    for ( String ch : line ) {
        if ( pairs.containsKey( ch ) ) {
            stack.push( ch )
        }
        else {
            def top = stack.pop()
            def closingBracket = pairs.get( top )
            if ( !closingBracket.equals( ch ) ) {
                pt1Points += points.get( ch )
                break
            }
        }
    }
    okay << stack
}

System.out.println("Part 1: " + pt1Points);

List<Long> scores = new ArrayList<>();
for (Stack<String> stack : okay) {
    long part2Pnts = 0;
    while (!stack.empty()) {
        String op = stack.pop();
        String cl = pairs.get(op);
        part2Pnts *= 5;
        part2Pnts += p2Points.get(cl);
    }
    scores.add(part2Pnts);
}
Collections.sort(scores);
System.out.println( scores.get(scores.size() / 2 as int));



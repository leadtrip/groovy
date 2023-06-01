import java.awt.Point

def input = new File( "../resources/day9input.txt" ).collect{it}

def testInput =
        ['R 4',
         'U 4',
         'L 3',
         'D 1',
         'R 4',
         'D 1',
         'L 5',
         'R 2']

def head = new Point(0, 0)
def tail = new Point(0, 0)
def visited = [tail.clone()] as Set

input.each{aLine ->
    def dirMove = aLine.split( ' ' )
    def direction = dirMove[0]
    def moves = dirMove[1] as Integer

    moves.times {
        move(head, direction)

        Point diff = diff(head, tail)

        if (diff.x.abs() > 1 || diff.y.abs() > 1) {
            tail.x += Math.signum(diff.x)
            tail.y += Math.signum(diff.y)
            visited << tail.clone()
        }
    }
}

println "Part 1: ${visited.size()}"

def diff( Point head, Point tail ) {
    new Point( head.x - tail.x as Integer, head.y - tail.y as Integer )
}

def move( Point p, String direction ) {
    switch (direction) {
        case 'U':
            p.y += 1
            break
        case 'D':
            p.y -= 1
            break
        case 'L':
            p.x -= 1
            break
        case 'R':
            p.x += 1
    }
}
package mikew.anything

import java.awt.Point

println Math.abs(0)
println Math.abs(1)
println Math.abs(-3.8)

def head = new Point(1,0 )
def tail = new Point(0,0 )     //TH
coords(head, tail)
head.x = 2
coords(head, tail)

tail.x += Math.signum(head.x)
tail.y += Math.signum(head.y)

coords(head, tail)
head.y++
coords(head, tail)
head.y++
coords(head, tail)
tail.x += Math.signum(head.x)
tail.y += Math.signum(head.y)
coords(head, tail)

def coords( head, tail ) {
    println '-----'
    println "head: $head tail: $tail"
    println "Next to each other on x axis? ${Math.abs(head.x - tail.x) > 1 ? 'no': 'yes'}"
    println "Next to each other on y axis? ${Math.abs(head.y - tail.y) > 1 ? 'no': 'yes'}"
}
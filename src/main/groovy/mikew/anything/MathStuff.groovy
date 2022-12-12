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

def coords( head, tail ) {
    println '-----'
    println "head: $head tail: $tail"
    println Math.abs(head.x - tail.x)
    println Math.abs(head.y - tail.y)

    println Math.signum(head.x)
    println Math.signum(head.y)
}
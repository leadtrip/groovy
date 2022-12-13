import groovy.transform.TupleConstructor

def input = new File( "../resources/day11input.txt" ).collect{it}

@TupleConstructor
class Monkey {
    List<Long> items
    Closure operation
    Closure targetMonkey
    Integer inspections = 0
}

def monkey0 = new Monkey( [85, 79, 63, 72], { item -> return item * 17 }, { item -> return item % 2 == 0 ? 2 : 6 } )
def monkey1 = new Monkey( [53, 94, 65, 81, 93, 73, 57, 92], { item -> return item * item }, { item -> return item % 7 == 0 ? 0 : 2 } )
def monkey2 = new Monkey( [62, 63], { item -> return item + 7 }, { item -> return item % 13 == 0 ? 7 : 6 } )
def monkey3 = new Monkey( [57, 92, 56], { item -> return item + 4 }, { item -> return item % 5 == 0 ? 4 : 5 } )
def monkey4 = new Monkey( [67], { item -> return item + 5 }, { item -> return item % 3 == 0 ? 1 : 5 } )
def monkey5 = new Monkey( [85, 56, 66, 72, 57, 99], { item -> return item + 6 }, { item -> return item % 19 == 0 ? 1 : 0 } )
def monkey6 = new Monkey( [86, 65, 98, 97, 69], { item -> return item * 13 }, { item -> return item % 11 == 0 ? 3 : 7 } )
def monkey7 = new Monkey( [87, 68, 92, 66, 91, 50, 68], { item -> return item + 2 }, { item -> return item % 17 == 0 ? 4 : 3 } )

def monkeyList = [monkey0, monkey1, monkey2, monkey3, monkey4, monkey5, monkey6, monkey7]

20.times {
    monkeyList.each{aMonkey ->
        def itemsIterator = aMonkey.items.iterator()
        while( itemsIterator.hasNext() ) {
            def newWorryLevel = aMonkey.operation( itemsIterator.next() ).intdiv(3)
            def targetMonkey = aMonkey.targetMonkey( newWorryLevel )
            monkeyList[targetMonkey].items << newWorryLevel
            aMonkey.inspections++
            itemsIterator.remove()
        }
    }
}

def top2 = monkeyList*.inspections.sort().reverse().take(2)
println "Part 1: ${top2[0] * top2[1]}"

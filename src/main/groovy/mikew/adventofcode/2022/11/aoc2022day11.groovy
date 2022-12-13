import groovy.transform.TupleConstructor

@TupleConstructor
class Monkey {
    List<Long> items
    Closure operation
    Closure targetMonkey
    Long inspections = 0
}

/*def monkey0 = new Monkey( [79, 98], { item -> return item * 19 }, { item -> return item % 23 == 0 ? 2 : 3 } )
def monkey1 = new Monkey( [54, 65, 75, 74], { item -> return item + 6 }, { item -> return item % 19 == 0 ? 2 : 0 } )
def monkey2 = new Monkey( [79, 60, 97], { item -> return item * item }, { item -> return item % 13 == 0 ? 1 : 3 } )
def monkey3 = new Monkey( [74], { item -> return item + 3 }, { item -> return item % 17 == 0 ? 0 : 1 } )

def monkeyList = [monkey0, monkey1, monkey2, monkey3]*/

def monkey0 = new Monkey( [85, 79, 63, 72], { item -> return item * 17 }, { item -> return item % 2 == 0 ? 2 : 6 } )
def monkey1 = new Monkey( [53, 94, 65, 81, 93, 73, 57, 92], { item -> return item * item }, { item -> return item % 7 == 0 ? 0 : 2 } )
def monkey2 = new Monkey( [62, 63], { item -> return item + 7 }, { item -> return item % 13 == 0 ? 7 : 6 } )
def monkey3 = new Monkey( [57, 92, 56], { item -> return item + 4 }, { item -> return item % 5 == 0 ? 4 : 5 } )
def monkey4 = new Monkey( [67], { item -> return item + 5 }, { item -> return item % 3 == 0 ? 1 : 5 } )
def monkey5 = new Monkey( [85, 56, 66, 72, 57, 99], { item -> return item + 6 }, { item -> return item % 19 == 0 ? 1 : 0 } )
def monkey6 = new Monkey( [86, 65, 98, 97, 69], { item -> return item * 13 }, { item -> return item % 11 == 0 ? 3 : 7 } )
def monkey7 = new Monkey( [87, 68, 92, 66, 91, 50, 68], { item -> return item + 2 }, { item -> return item % 17 == 0 ? 4 : 3 } )

def monkeyList = [monkey0, monkey1, monkey2, monkey3, monkey4, monkey5, monkey6, monkey7]

println run( monkeyList, 1 )
//println run( monkeyList, 2 )

def run( List<Monkey> monkeyList, Integer part ) {
    meta(part).iterations.times {
        monkeyList.each { aMonkey ->
            def itemsIterator = aMonkey.items.iterator()
            while (itemsIterator.hasNext()) {
                Long newWorryLevel = aMonkey.operation(itemsIterator.next())
                if ( meta(part).worry )
                    newWorryLevel = newWorryLevel.intdiv(3)
                else
                    newWorryLevel = newWorryLevel % 9699690
                def targetMonkey = aMonkey.targetMonkey(newWorryLevel)
                monkeyList[targetMonkey].items << newWorryLevel
                aMonkey.inspections++
                itemsIterator.remove()
            }
        }
    }
    monkeyList*.inspections.sort().reverse().take(2).stream().reduce(1, (a,b) -> a * b)
}

def meta( Integer part ) {
    [1: [iterations: 20, worry: true], 2: [iterations: 10000, worry: false]][part]
}


package mikew.anything

def strList = ['cheese', 'bread', 'butter']
def anyMap = [1: 'tree', 2: 'grass']

strList.each { item ->
    if(item.equals('bread')) {
        return                  // return just takes us out of the current iteration
    }
    println item
}

println strList.findAll {!it.equals('bread')}

println strList.collect {it.toUpperCase()}

println strList.findResult {it.equals('bread') ? 'Got bread' : 'No bread'}

println strList.grep{ it.length() > 5 }

anyMap.each {k, v -> println "${k} ${v}"}
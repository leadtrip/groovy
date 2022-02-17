package mikew.anything

// LinkedList as a FIFO queue
Queue numberQueue = new LinkedList<Integer>()
numberQueue.offer( 1 )
numberQueue.offer( 2 )
numberQueue.offer( 3 )
numberQueue.offer( 4 )

println numberQueue.poll()


Queue stringQueue = new LinkedList<String>()
stringQueue.offer( 'Elliot' )
stringQueue.offer( 'Mike' )
stringQueue.offer( 'Owen' )
stringQueue.offer( 'Holden' )

println stringQueue.poll()

// ----------------------------------------------------------

// stack is LIFO
Stack numberStack = new Stack<Integer>()
numberStack.push( 1 )
numberStack.push( 2 )
numberStack.push( 3 )
numberStack.push( 4 )

println numberStack.pop()

Stack stringStack = new Stack<String>()
stringStack.push( 'Elliot' )
stringStack.push( 'Mike' )
stringStack.push( 'Owen' )
stringStack.push( 'Holden' )

println stringStack.pop()
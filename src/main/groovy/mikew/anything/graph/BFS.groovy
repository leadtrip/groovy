package mikew.anything.graph

import groovy.transform.Canonical

Vertex<Integer> v0 = new Vertex<>(0)
Vertex<Integer> v1 = new Vertex<>(1)
Vertex<Integer> v2 = new Vertex<>(2)
Vertex<Integer> v3 = new Vertex<>(3)
Vertex<Integer> v4 = new Vertex<>(4)
Vertex<Integer> v5 = new Vertex<>(5)
Vertex<Integer> v6 = new Vertex<>(6)

/**
 *
        0
      / | \
    1 - 5  6
    | \  /
    3   4
         \
          2

 */

v0.setNeighbors([v1, v5, v6])
v1.setNeighbors([v3, v4, v5])
v4.setNeighbors([v2, v6])
v6.setNeighbors([v0])

BreadthFirstSearch<Integer> bfs = new BreadthFirstSearch<>(v1)
bfs.traverse()

@Canonical(excludes='neighbors', includePackage=false)
class Vertex<T> {
    final T data
    boolean visited
    List<Vertex<T>> neighbors = new LinkedList<>()
}


class BreadthFirstSearch<T> {

    Vertex<T> startVertex
    BreadthFirstSearch(Vertex<T> st) {
        startVertex = st
    }

    void traverse() {
        Queue<Vertex<T>> queue = new LinkedList<>()
        queue.add(startVertex)
        while(!queue.isEmpty()) {
            Vertex<T> current = queue.poll()
            if( !current.isVisited() ) {
                current.setVisited(true)
                println current
                queue.addAll(current.neighbors)
            }
        }
    }
}
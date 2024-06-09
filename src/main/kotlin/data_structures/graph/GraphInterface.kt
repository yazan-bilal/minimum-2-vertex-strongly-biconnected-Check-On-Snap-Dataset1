package data_structures.graph

import data_structures.linked_list.LinkedList

interface GraphInterface<T> {
    fun createVertex(data:T):Vertex<T>
    fun addDirectedEdge(source:Vertex<T>,
                        destination:Vertex<T>,
                        weight:Double?
                        )
    fun addUndirectedEdge(source:Vertex<T>,
                          destination: Vertex<T>,
                          weight: Double?)
    fun add(edge: EdgeType ,
            source : Vertex<T>,
            destination : Vertex<T> ,
            weight : Double?
            )
    fun edges(source:Vertex<T>): LinkedList<Edge<T>>

    fun weight(
        source:Vertex<T>,
        destination: Vertex<T>,
    ):Double?
}

enum class EdgeType {
    DIRECTED, UNDIRECTED
}
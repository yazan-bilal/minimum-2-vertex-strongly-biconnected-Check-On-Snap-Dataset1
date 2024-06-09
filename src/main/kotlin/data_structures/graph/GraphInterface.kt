package data_structures.graph

import data_structures.linked_list.LinkedList

interface GraphInterface<T> {
    fun createVertex(data:T):Vertex<T>
    fun addDirectedEdge(source:Vertex<T>,
                        destination:Vertex<T>,
                        )

    fun edges(source:Vertex<T>): LinkedList<Edge<T>>

}

enum class EdgeType {
    DIRECTED, UNDIRECTED
}
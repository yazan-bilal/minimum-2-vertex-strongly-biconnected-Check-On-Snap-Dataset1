package data_structures.graph

import data_structures.linked_list.LinkedList
import java.util.*

class Graph<T>:GraphInterface<T>
{
    var type : GraphType = GraphType.Directed
            private set
    var eCount :Int  = 0
        private set

    var vCount :Int = 0
        private set
    var dfsCounter = 0
    var finishCounter = 0

    var oStack = Stack<Vertex<T>>()
    var rStack = Stack<Vertex<T>>()

    var treeEdges = LinkedList<Edge<T>>()
    var backwordEdge = LinkedList<Edge<T>>()


    private val adjacencies:MutableMap< Vertex<T>,LinkedList<Edge<T>> > = HashMap()
    override fun createVertex(data: T): Vertex<T> {
        val vertex = Vertex(adjacencies.count(),data) 
        adjacencies[vertex] = LinkedList()
        vCount = adjacencies.count()
        return vertex

    }


    fun addVertex(vertex:Vertex<T>):Vertex<T>{
        val v = vertex.copy(index = adjacencies.count())
        adjacencies[v] = LinkedList()
        return v
    }

    override fun addDirectedEdge(source: Vertex<T>, destination: Vertex<T>) {
        val edge = Edge<T>(source,destination)
        adjacencies[source] = adjacencies[source] ?: LinkedList()
        adjacencies[source]?.add(edge)
        eCount++

    }


    override fun edges(source: Vertex<T>) = adjacencies[source] ?: LinkedList<Edge<T>>( )
    fun vertexes() = adjacencies.keys


    fun getVertexByData(data: T):Vertex<T>? {
        for ((vertex,edges) in adjacencies){
            if (vertex.data == data){
                return vertex
            }
        }
        return null
    }

    fun removeVertex(vertex:Vertex<T>) {
        eCount -= adjacencies[vertex]?.count()!!
        adjacencies.remove(vertex)
        vCount--
        adjacencies.forEach {( _ , edges )->
            for (edge in edges) {
                if (edge.destination == vertex) {
                    edges.remove(edge )
                    eCount--
                }
            }
        }
    }

    fun removeEdge(edge: Edge<T>):Boolean?{
        eCount --
        return adjacencies[edge.source]?.remove(edge)
    }
    fun toUndirectedGraph() {
        adjacencies.forEach{( vertex , edges ) ->
            for(edge in edges){
                val new = edge.copy(source = edge.destination,destination = edge.source)
                if(adjacencies[new.source]?.contains(new)!!) {
                }else {
                    addDirectedEdge(new.source,new.destination)
                }
            }
        }
        var totalEdges = 0
        adjacencies.forEach{ (vertex,_) ->
            totalEdges += adjacencies[vertex]?.count()!!
        }
        eCount = totalEdges/2
        type = GraphType.Undirected
    }

    fun convertToDirectedGraph(
    ): Graph<T> {
        val vertices = adjacencies.size
        val directedAdjList = Array(vertices) { LinkedList<Int>() }
        val visited = BooleanArray(vertices)
        val dfsParent = IntArray(vertices) { -1 }
        val dfsNumbers = IntArray(vertices) { -1 }
        val hasBE = IntArray(vertices) { 0 }
        var dfsCounter = 0

        fun dfs(v: Vertex<T>) {
            visited[v.index] = true
            dfsNumbers[v.index] = dfsCounter++
            var adjacencies = adjacencies[v]!!
            for (neighbor in adjacencies) {
                if (!visited[neighbor.destination.index]) {
                    dfsParent[neighbor.destination.index] = v.index
                    directedAdjList[v.index].add(neighbor.destination.index) // Tree edge
                    dfs(neighbor.destination)
                } else if (dfsParent[v.index] != neighbor.destination.index && dfsNumbers[neighbor.destination.index] < dfsNumbers[v.index]) {
                    directedAdjList[v.index].add(neighbor.destination.index) // Back edge
                    hasBE[v.index]++
                }
            }
        }

        for (i in adjacencies.keys.indices) {
            if (!visited[i]) {
                dfs(vertexes().elementAt(i))
            }
        }

        return this
    }
}

enum class GraphType {
    Undirected , Directed
}
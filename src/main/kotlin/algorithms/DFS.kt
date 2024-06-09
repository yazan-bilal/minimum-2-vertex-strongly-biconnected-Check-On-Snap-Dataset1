package algorithms

import data_structures.graph.Graph
import data_structures.graph.GraphType
import data_structures.graph.Vertex
import data_structures.graph.VertexState
import data_structures.linked_list.LinkedList

// DFSDirected written by Yamama
// DfSUndirected written by yara mia
fun <T> Graph<T>.DFS(){
    if (type == GraphType.Directed){
        DFSDirected()
    }else {
        DfSUndirected()
    }
}

// unDirected :
fun <T> Graph<T>.DfSUndirected(){
    Depth_First_Search_Undirected_init()
    for(v in vertexes()){
        if (v.state == VertexState.NEW){
            depth_first_search_Undirected(v)
        }
    }
}

fun <T> Graph<T>.depth_first_search_Undirected(vertex: Vertex<T>) {
    vertex.state = VertexState.ACTIVE
    vertex.dfsNumber = dfsCounter
    dfsCounter++
    for (edge in edges(vertex)){
        if(edge.destination.state == VertexState.NEW) {
            depth_first_search_Undirected(edge.destination)
            treeEdges.add(edge)
        }else if (edge.destination.state == VertexState.ACTIVE){
            backwordEdge.add(edge)
        }
    }
    vertex.state = VertexState.FINISHED
    vertex.finishNumber = finishCounter
    finishCounter ++
}

fun <T>Graph<T>.Depth_First_Search_Undirected_init(){
    for (v in vertexes()){
        v.state = VertexState.NEW
        v.dfsNumber = 0
        v.finishNumber = 1
    }
    dfsCounter = 0
    finishCounter = 0
    treeEdges = LinkedList()
    backwordEdge = LinkedList()

}


// directed
fun <T> Graph<T>.DFSDirected(){
    Depth_First_Search_directed_init()
    for(v in vertexes()){
        if (v.state == VertexState.NEW){
            depth_first_search_directed(v)
        }
    }
}
fun <T>Graph<T>.Depth_First_Search_directed_init(){
    for (v in vertexes()){
        v.state = VertexState.NEW
        v.dfsNumber = 0
        v.finishNumber = 1
    }
    dfsCounter = 0
    finishCounter = 0

}
fun <T> Graph<T>.depth_first_search_directed(vertex:Vertex<T>){
    vertex.state = VertexState.ACTIVE
    vertex.dfsNumber = dfsCounter
    dfsCounter++
    for (edge in edges(vertex)){
        if(edge.destination.state == VertexState.NEW) {
            depth_first_search_directed(edge.destination)
        }
    }
    vertex.state = VertexState.FINISHED
    vertex.finishNumber = finishCounter
    finishCounter ++
}
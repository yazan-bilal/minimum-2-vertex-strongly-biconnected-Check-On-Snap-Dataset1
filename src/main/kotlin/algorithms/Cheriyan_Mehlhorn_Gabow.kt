package algorithms.graph_algorithms

import data_structures.graph.Graph
import data_structures.graph.Vertex
import data_structures.graph.VertexState
import java.util.*

fun <T> Graph<T>.cmg_init(){
    dfsCounter = 1
    for (v in vertexes()){
        v.state = VertexState.NEW
        v.dfsNumber = -1
        v.compR = null
        oStack = Stack<Vertex<T>>()
        rStack = Stack<Vertex<T>>()
    }
}

fun <T> Graph<T>.cheriyanMehlhornGabow(){
    cmg_init()
    for (v in vertexes()){
        if (v.state == VertexState.NEW) {
            depth_first_search_mod(v)
        }
    }
}

fun <T> Graph<T>.depth_first_search_mod(vertex: Vertex<T>){
    vertex.state = VertexState.ACTIVE
    vertex.dfsNumber = dfsCounter
    dfsCounter++
    oStack.push(vertex)
    rStack.push(vertex)
    edges(vertex).forEach {edge ->
        if(edge.destination.state == VertexState.NEW){
            depth_first_search_mod(edge.destination)
        }else {
            if (edge.destination in oStack){
                while(edge.destination.dfsNumber < rStack.last().dfsNumber){
                    rStack.pop()
                }
            }
        }
    }
    vertex.state = VertexState.FINISHED
    if (vertex == rStack.last()){
        rStack.pop()
        var w = oStack.last()
        while(vertex != w){
            w = oStack.pop()
            w.compR = vertex
        }
    }

}

fun <T> Graph<T>.largestStronglyConnectedGraph():Graph<T>{
    var pair = hashMapOf<Vertex<T>,Int>()
    // number of vertexes for each strongly connected graph
    for (v in vertexes()) {
        if (v.compR != null && pair.containsKey(v.compR)) {
            pair[v.compR!!] = pair[v.compR!!]!! + 1
        }else if (v.compR != null && !pair.containsKey(v.compR)){
            pair[v.compR!!] = 1
        }
    }
    // finding the representative Vertex for the largest strongly connected graph
    var max = 0
    for (i in pair.values){
        if (i > max){
            max = i
        }
    }
    var ROfLargest:Vertex<T>? = null
    for ((v,_ ) in pair){
        if (pair[v] == max ){
            ROfLargest = v
        }
    }
    // creating the sub graph :
    val subGraph = Graph<T>()
    for (v in vertexes()){
        if ( v.compR == ROfLargest){
            subGraph.addVertex(v)
        }
        for (v in vertexes()){
            if (v in subGraph.vertexes()){
                for (edge in  edges(v)){
                    if (edge.destination  in subGraph.vertexes()){
                        subGraph.addDirectedEdge(edge.source , edge.destination)
                    }
                }
            }
        }
    }

    return subGraph
}
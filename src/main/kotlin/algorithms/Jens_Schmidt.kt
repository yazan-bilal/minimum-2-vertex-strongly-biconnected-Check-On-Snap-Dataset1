package algorithms.graph_algorithms

import data_structures.graph.Graph
import data_structures.graph.GraphType


fun <T> Graph<T>.js(){
    if (type == GraphType.Directed)
        return
    DFS()

}
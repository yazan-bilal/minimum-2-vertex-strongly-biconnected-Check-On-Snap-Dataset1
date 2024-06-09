package algorithms

import data_structures.graph.Graph
import data_structures.graph.GraphType
import data_structures.linked_list.LinkedList

// written by Sara
fun <T> Graph<T>.isBiconnectd():Boolean{
    DFS()
    val directedgraph = this.convertToDirectedGraph()
    directedgraph.chainDFS(this.vertexes().first().index)
    return true
}

// written by Ahmad Yousef
fun <T> Graph<T>.chainDFS(
    startVertex: Int
): List<Int> {
    val visited = BooleanArray(this.eCount)
    val path = mutableListOf<Int>()

    fun dfs(v: Int) {
        visited[v] = true
        path.add(v)
        for (neighbor in vertexes()) {
            if (!visited[neighbor.index]) {
                dfs(neighbor.index)
            }
        }
    }

    dfs(startVertex)
    return path
}




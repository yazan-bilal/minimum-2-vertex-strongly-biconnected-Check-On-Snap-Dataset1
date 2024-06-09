import algorithms.extractToGraph
import algorithms.graph_algorithms.cheriyanMehlhornGabow
import algorithms.graph_algorithms.largestStronglyConnectedGraph
import algorithms.isBiconnectd
import data_structures.graph.Graph
import util.*
import java.io.File
// written by Yazan Bilal
fun main(args: Array<String>) {
    val file = File(WIKI_VOTE_PATH)
    val lines = file.readLines()
    val graph = Graph<Int>()
    graph.extractToGraph(lines)
    graph.cheriyanMehlhornGabow()
    var Scc = graph.largestStronglyConnectedGraph()
    graph.is_minimum_2_vertexs_strongly_connected()
}

fun <T> Graph<T>. is_minimum_2_vertexs_strongly_connected(): Boolean {
    for(v in this.vertexes()){
        this.removeVertex(v)
        if(!this.isBiconnectd()){
            return false
        }
    }
    return true
}






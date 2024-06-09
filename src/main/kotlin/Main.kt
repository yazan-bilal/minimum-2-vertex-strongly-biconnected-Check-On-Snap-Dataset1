import algorithms.extractToGraph
import data_structures.graph.Graph
import util.*
import java.io.File

fun main(args: Array<String>) {
    val file = File(WIKI_VOTE_PATH)
    val lines = file.readLines()
    val graph = Graph<Int>()
    graph.extractToGraph(lines)
    println("number of nodes ${graph.vCount} number of edges ${graph.eCount}")


    val file2 = File(WIKI_VOTE_PATH)
    val lines2 = file.readLines()
    val graph2 = Graph<Int>()
    graph.extractToGraph(lines)
    println("number of nodes ${graph2.vCount} number of edges ${graph2.eCount}")


}


// extracting the dataset into a graph





package algorithms

import data_structures.graph.Graph

fun Graph<Int>.extractToGraph(data:List<String>){
    for (line in data) {
        val numbers = getNumbers(line)
        if (!dataExist(numbers[0])){
            createVertex(numbers[0])
        }
        if (!dataExist(numbers[1])){
            createVertex(numbers[1])
        }
        addDirectedEdge(getVertexByData(numbers[0])!!,getVertexByData(numbers[1])!!,weight = null)
    }
}

fun  <T> Graph<T>.dataExist(data:Int):Boolean{
    for (v in vertexes()){
        if (v.data == data){
            return true
        }
    }
    return false
}
fun getNumbers(str: String): List<Int> {
    return str.split(Regex("\\D+"))
        .filter { it.isNotBlank() }
        .map { it.toInt() }
}
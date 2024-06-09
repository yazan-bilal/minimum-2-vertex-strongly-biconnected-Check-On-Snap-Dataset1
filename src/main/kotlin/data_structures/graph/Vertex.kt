package data_structures.graph

data class Vertex<T>(
    val index:Int ,
    val data:T,
    var state : VertexState = VertexState.NEW,
    var dfsNumber:Int = -1,
    var finishNumber:Int = -1,
    var compR :Vertex<T>? = null
)

enum class VertexState {
    NEW , ACTIVE , FINISHED
}
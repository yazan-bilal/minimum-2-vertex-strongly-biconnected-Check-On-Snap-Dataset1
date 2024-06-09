package data_structures.linked_list

// written by Ahmad Yousef
class ListNode<T>(
    var value:T,
    var next: ListNode<T>? = null,
) {
    override fun toString(): String {
        return if (next != null){
            "$value -> ${next.toString()}"
        } else {
            "$value"
        }
    }

    fun printInReverse(){
        next ?.printInReverse()

        if (next != null){
            print(" -> ")
        }
        print(value.toString())
    }

}
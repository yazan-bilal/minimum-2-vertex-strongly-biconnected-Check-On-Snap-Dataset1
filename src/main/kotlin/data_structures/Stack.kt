package data_structures

class  Stack<T:Any> {
    val stack:ArrayList<T> = ArrayList()
    fun push(value:T){
        stack.add(value)
    }
    fun pop():T{
        return stack.removeLast()
    }

    fun top():T{
        return stack.last()
    }
}
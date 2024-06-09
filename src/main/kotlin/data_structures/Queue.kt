package data_structures

import data_structures.linked_list.LinkedList
import data_structures.linked_list.ListNode

class Queue<T:Any>(

) {

    private val queue = LinkedList<T>()

    override fun toString(): String {
        return queue.toString()
    }
    var head:ListNode<T>? = queue.head
    var tail :ListNode<T>? = queue.tail
    fun enqueue(value:T):Queue<T>{
        queue.append(value)
        tail = queue.tail
        return this
    }

    fun dequeue():ListNode<T>?{
        if (queue.isEmpty()){
            return null
        }
        val deletedNode = head
        queue.head = queue.head?.next
        head = queue.head
        return deletedNode
    }


    fun isEmpty():Boolean {
        return queue.isEmpty()
    }
}
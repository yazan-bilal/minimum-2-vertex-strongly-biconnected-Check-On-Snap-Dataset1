package data_structures.linked_list


class LinkedList<T> : MutableIterable<T>, Collection<T>, MutableCollection<T> {
    var head: ListNode<T>? = null
    var tail: ListNode<T>? = null
    override var size = 0
        private set

    override fun clear() {
        head = null
        tail = null
        size = 0
    }

    override fun addAll(elements: Collection<T>): Boolean {
        for (element in elements) {
            add(element)
        }
        return true
    }

    override fun add(element: T): Boolean {
        append(element)
        return true
    }

    override fun isEmpty(): Boolean = size == 0


    // push is adding to the front
    fun push(value: T): LinkedList<T> {
        head = ListNode(value, this.head)
        if (tail == null) {
            tail = head
        }
        size++
        return this
    }

    //append adding to the end of Linked list

    fun append(value: T): LinkedList<T> {
        if (isEmpty()) {
            push(value)
            return this
        }
        tail!!.next = ListNode(value, null)
        tail = tail!!.next
        size++
        return this
    }

    // insert a value in a specific place :
    fun insertNode(value: T, index: Int): LinkedList<T> {
        var pointer = head
        if (isEmpty()) {
            push(value)
        }
        if (index >= size) {
            println("index out of bounds")
            return this
        }
        for (i in 0..index) {
            pointer = pointer?.next
            if (i == index - 1) {
                pointer!!.next = ListNode(value, pointer.next)
            }
        }
        return this
    }

    fun nodeAt(index: Int): ListNode<T> {
        var currentNode = head
        var currentIndex = 0
        while (currentNode != null && currentIndex < index) {
            currentNode = currentNode.next
            currentIndex++
        }
        return currentNode!!
    }

    fun insert1(value: T, afterNode: ListNode<T>): ListNode<T> {
        if (tail == afterNode) {
            append(value)
            return tail!!
        }
        val newNode = ListNode(value = value, next = afterNode)
        afterNode.next = newNode
        size++

        return newNode
    }

    fun insert(value: T, index: Int = 0): LinkedList<T> {
        if (size - 1 == index || isEmpty()) {
            append(value)
        } else {
            val newNode = ListNode(value, nodeAt(index))
            nodeAt(index - 1).next = newNode
            size++
        }
        return this
    }

    // deleting values :
    fun pop(): ListNode<T>? {
        if (head == null)
            return null
        val deletedNode = head
        head = head?.next
        size--
        return deletedNode
    }

    fun deleteLast(): ListNode<T>? {
        if (isEmpty()) {
            return null
        }
        if (tail == head) {
            return pop()
        }
        tail = nodeAt(size - 2)
        val deletedNode = tail
        tail!!.next = null
        size--
        return deletedNode
    }

    fun deleteAt(index: Int): ListNode<T> {
        return if (size == 1) {
            deleteLast()!!
        } else {
            val previousNode = nodeAt(index - 1)
            val deletedNode = previousNode.next
            previousNode.next = previousNode.next!!.next
            size--
            deletedNode!!

        }
    }

    fun deleteValue(value: T) {
        var current = head
        if (size == 0) {
            return
        }
        if (head?.value == value) {
            pop()
        }
        if (current?.next == null) {
            return
        }
        for (i in 0 until size) {
            if (current?.next?.value == value) {
                current?.next = current?.next?.next
            } else if (current?.next == null) {
                return
            } else {
                current = current?.next
            }
        }
    }


    fun valueExist(value: T): Boolean {
        var pointer = head
        for (i in 0 until size) {
            if (pointer?.value == value) {
                return true
            }
            pointer = pointer?.next
        }
        return false
    }

    fun copyWithout(value: Int): LinkedList<T> {
        var pointer = head
        var newLinkedList = LinkedList<T>()
        for (i in indices) {
            if (pointer?.value != value) {
                newLinkedList.append(pointer!!.value)
            }
            if (pointer.next != null) // why this line ? because if i removed it I will get a null pointer exception beceause i have a chain for checking for null value that are implicit
                pointer = pointer?.next
        }
        return newLinkedList
    }


    // implementing the function needed for the Iterable interface
    override fun iterator(): MutableIterator<T> {
        return LinkedListIterator(this)
    }

    // delete every thing is not in the list :
    override fun retainAll(elements: Collection<T>): Boolean {
        var result = false
        val iterator = this.iterator()
        while (iterator.hasNext()){
            val item = iterator.next()
            if (!elements.contains(item)) {
                iterator.remove()
                result = true
            }
        }
        return false
    }

    // the value if removeAll will be true only of one of the elements removed..

    override fun removeAll(elements: Collection<T>): Boolean {
        var result = false
        for (item in elements){
            result = remove(item) || result
        }
        return result
    }

    override fun remove(element: T): Boolean {
        val iterator = iterator()
        while (iterator.hasNext()){
            val item = iterator.next()
            if (item == element) {
                iterator.remove()
                return true
            }
        }
        return  false
    }

    inner class LinkedListIterator<T>(
        private val list: LinkedList<T>,
        private var index: Int = 0,
        private var lastNode: ListNode<T>? = null
    ) : MutableIterator<T> {
        override fun hasNext(): Boolean {
            return index < list.size
        }

        override fun next(): T {
            if (index >= list.size) throw IndexOutOfBoundsException()
            lastNode = if (index == 0) {
                list.nodeAt(0)
            } else {
                lastNode?.next
            }
            index++
            return lastNode!!.value
        }

        // for the MutableItrator
        override fun remove() {
            if (index == 0) {
                pop()
            } else {
                list.deleteAt(index)
            }
            index--
        }
    }

    // implementing the Collection Interface :
    override fun contains(element: T): Boolean {
        for (i in this) {
            if (element == i)
                return true
        }
        return false
    }

    override fun containsAll(elements: Collection<T>): Boolean {
        for (searched in elements) {
            if (!contains(searched))
                return false
        }
        return true
    }

}
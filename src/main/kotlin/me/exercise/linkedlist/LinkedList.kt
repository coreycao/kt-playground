package me.exercise.linkedlist

/**
 * 打印链表
 */
fun <T> Node<T>?.printLinkedList() {
    if (this == null) {
        println("null")
    } else {
        var cur = this
        while (cur != null) {
            print("${cur.value}${if (cur.next == null) " -> null\n" else " -> "}")
            cur = cur.next
        }
    }
}

/**
 * 从数组构造链表
 */
fun <T> Array<T>.toLinkedList(): Node<T>? {
    if (this.isEmpty()) return Node.emptyLinkedList()
    val head: Node<T>? = Node<T>(this[0])
    var current = head
    for (i in 1 until this.size) {
        current!!.next = Node<T>(this[i])
        current = current.next
    }
    return head
}

/**
 * Node for LinkedList
 */
class Node<T>(var value: T, var next: Node<T>? = null) {

    // data for test
    companion object {

        fun <T> emptyLinkedList(): Node<T>? {
            val head: Node<T>? = null
            return head
        }

        fun <T> singleNodeLinkedList(t: T): Node<T> {
            return Node(t)
        }

        fun getLinkedList(): Node<Int> {
            return arrayOf(1, 2, 3, 4, 5, 6).toLinkedList()!!
        }

        fun getPalindromeLinkedList(): Node<Int> {
            return arrayOf(1, 2, 3, 4, 3, 2, 1).toLinkedList()!!
        }

        fun getLinkedListWithDuplicateElements(): Node<Int> {
            return arrayOf(7, 3, 2, 3, 5, 5, 5, 1, 2, 8, 6, 7, 6).toLinkedList()!!
        }

        fun getLinkedListWithRing(): Node<Int> {
            val head = Node(1)
            head.next = Node(2)
            val node3 = Node(3)
            head.next?.next = node3
            head.next?.next?.next = Node(4)
            head.next?.next?.next?.next = node3
            return head
        }
    }
}


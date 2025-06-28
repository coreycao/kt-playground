package me.exercise.linkedlist

fun main() {

    // 1 -> null
    deleteNodeByValue(Node.singleNodeLinkedList(1), 2).printLinkedList()
    // null
    deleteNodeByValue(Node.singleNodeLinkedList(1), 1).printLinkedList()

    val list = Node.getLinkedListWithDuplicateElements()
    println("before")
    list.printLinkedList()

    var target = 6
    println("after delete $target")
    val list1 = deleteNodeByValue(list, target).apply {
        printLinkedList()
    }

    target = 7
    println("after delete $target")
    val list2 = deleteNodeByValue(list1, target).apply {
        printLinkedList()
    }

    target = 5
    println("after delete $target")
    val list3 = deleteNodeByValue(list2, target).apply {
        printLinkedList()
    }
}

/**
 * 删除链表中给定值的结点
 *
 * 思路 1：使用额外的数据结构（栈或者队列）辅助
 * 思路 2：递归
 * 思路 3：使用辅助头结点遍历
 *
 * https://leetcode.cn/problems/remove-linked-list-elements/description/
 */
fun <T : Comparable<T>> deleteNodeByValueWithQueue(head: Node<T>?, target: T): Node<T>? {
    if (head == null) return head
    var current = head
    val queue = ArrayDeque<Node<T>>()
    while (current != null) {
        if (current.value != target) {
            queue.addFirst(current)
        }
        current = current.next
    }

    if (queue.isEmpty()) return null

    val ret = queue.removeLast()
    current = ret
    while (queue.isNotEmpty()) {
        current!!.next = queue.removeLast()
        current = current.next
    }
    // 断开结点原引用，使其成为新的尾节点
    current?.next = null
    return ret
}

fun <T : Comparable<T>> deleteNodeByValue(head: Node<T>?, target: T): Node<T>? {
    val dummyHead = Node(target)
    dummyHead.next = head
    var current = dummyHead
    while (current.next != null) {
        if (current.next!!.value == target) {
            current.next = current.next!!.next
        } else {
            current = current.next!!
        }
    }
    return dummyHead.next
}


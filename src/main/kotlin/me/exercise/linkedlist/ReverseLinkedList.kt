package me.exercise.linkedlist

fun main() {
    val list = Node.getLinkedList()
    list.printLinkedList()
    println("\nafter reversed")
    val reverseList = reverseByRecursive(list)
    reverseList.printLinkedList()
}

/**
 * 反转链表
 *
 * 四步走：
 * 1）拿到下一个节点
 * 2）调转朝向：当前节点指向前一个节点
 * 3）存起来当前节点，以备下一个节点的转向使用
 * 4）向后移动指针，用到了步骤 1）中拿到的下一个节点
 */
fun <T> reverse(head: Node<T>?): Node<T>? {
    var current = head
    var previous: Node<T>? = null
    while (current != null) {
        // 拿到下一个节点
        var next = current.next
        // 调转朝向
        current.next = previous
        // 更新 preview，留着下次使用
        previous = current
        // 向后移动指针
        current = next
    }
    return previous
}

/**
 * 使用递归的思路来反转链表
 */
fun <T> reverseByRecursive(head: Node<T>?): Node<T>? {
    val next = head?.next ?: return head
    val newHead = reverseByRecursive(next)
    next.next = head
    head.next = null
    return newHead
}


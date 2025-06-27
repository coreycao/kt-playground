package me.exercise.linkedlist

fun main() {
    val list = arrayOf(5, 7, 3, 1, 9).toLinkedList()

    // 5 - 7 - 1 - 9
    val list1 = deleteLastKthNode(list, 3).apply {
        printLinkedList()
    }
    // 7 - 1 - 9
    val list2 = deleteLastKthNode(list1, 4).apply {
        printLinkedList()
    }
    // 7 - 1
    deleteLastKthNode(list2, 1).printLinkedList()
}

/**
 * 删除链表中倒数第 K 个结点
 */
fun <T> deleteLastKthNode(head: Node<T>?, lastKth: Int): Node<T>? {
    if (head == null || lastKth < 1) {
        return head
    }
    var k = lastKth
    var current = head
    while (current != null) {
        k--
        current = current.next
    }

    if (k > 0) {
        // k > 0, 不合法, 超出链表节点数量
        return head
    } else if (k == 0) {
        // k == 0, 头结点, 直接移除头结点即可
        return head.next
    } else {
        // k < 0, 再次遍历, 找到指定位置
        // 举例：1 - 2 - 3, 删除倒数第1个，第一次遍历后k为-2，第二次遍历后current指向 2
        current = head
        while (++k != 0) {
            current = current?.next
        }
        current?.next = current.next?.next
        return head
    }
}
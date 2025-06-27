package me.exercise.linkedlist

fun main() {
    val list1 = arrayOf(1, 3, 5, 7).toLinkedList()
    val list2 = arrayOf(2, 4, 6, 8).toLinkedList()

    // null
    // mergeTwoOrderedLinkedList(Node.emptyLinkedList<Int>(), Node.emptyLinkedList()).printLinkedList()

    // 1,3,5,7
    // mergeTwoOrderedLinkedList(list1, Node.emptyLinkedList()).printLinkedList()

    // 2,4,6,8
    // mergeTwoOrderedLinkedList(Node.emptyLinkedList(), list2).printLinkedList()

    // 1,2,3,4,5,6,7,8
    mergeTwoOrderedLinkedList(list1, list2).printLinkedList()
}

/**
 * 合并两个有序链表
 *
 * 思路：
 */
fun <T : Comparable<T>> mergeTwoOrderedLinkedList(head1: Node<T>?, head2: Node<T>?): Node<T>? {
    if (head1 == null || head2 == null) {
        return head1 ?: head2
    }

    // head 指向较小的一方
    val head = if (head1.value <= head2.value) head1 else head2
    // cur1 较小一方的指针
    var cur1: Node<T>? = if (head == head1) head1 else head2
    // cur2 较大一方的指针
    var cur2: Node<T>? = if (head == head1) head2 else head1
    // 记录较小一方
    var pre = cur1

    while (cur1 != null && cur2 != null) {
        if (cur1.value <= cur2.value) {
            // 如果 cur1 较小，则 cur1 向后移动即可
            pre = cur1
            cur1 = cur1.next
        } else {
            // 如果 cur2 较小，则 cur2 接到 pre 后，再接上 pre1
            // 然后 pre 记录当前的较小节点: cur2

            var tmp = cur2.next

            pre?.next = cur2
            cur2.next = cur1
            pre = cur2

            cur2 = tmp
        }
    }

    // 处理尾巴
    pre?.next = cur1 ?: cur2

    return head
}
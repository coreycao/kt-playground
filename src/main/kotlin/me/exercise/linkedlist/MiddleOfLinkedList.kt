package me.exercise.linkedlist

fun main() {
    // null
    middleNodeWithTwoPointer(Node.emptyLinkedList<Int>()).printLinkedList()
    // 1
    middleNodeWithTwoPointer(arrayOf(1).toLinkedList()).printLinkedList()
    // 2
    middleNodeWithTwoPointer(arrayOf(1, 2, 3).toLinkedList()).printLinkedList()
}

/**
 * 返回链表的中间节点，存在两个中间节点，则返回后一个
 *
 * 思路一：最直观的想法，通过遍历定位链表的中间位置
 * 思路二：快慢指针，fast 每次移动两步，当它到达尾部时，slow 停留在中间位置
 *
 * https://leetcode.cn/problems/middle-of-the-linked-list/description/
 */
fun <T> middleNode(head: Node<T>?): Node<T>? {
    if (head == null || head.next == null) return head
    var length = 0
    var current = head
    while (current != null) {
        length++
        current = current.next
    }
    println("lengthOfLinkedList: $length")
    val targetIdx = length / 2
    var idx = 0
    var targetNode = head
    while (idx < targetIdx) {
        idx++
        targetNode = targetNode!!.next
    }

    return targetNode
}

fun <T> middleNodeWithTwoPointer(head: Node<T>?): Node<T>? {
    var slow = head
    var fast = head
    while (fast != null && fast.next != null) {
        slow = slow!!.next
        fast = fast.next!!.next
    }
    return slow
}


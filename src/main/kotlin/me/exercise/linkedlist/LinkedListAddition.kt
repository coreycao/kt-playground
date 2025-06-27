package me.exercise.linkedlist

fun main() {
    val num1 = arrayOf(9, 9, 9, 9, 9, 9, 9).toLinkedList()
    val num2 = arrayOf(9, 9, 9, 9).toLinkedList()
    addTwoNumbers(num1, num2).printLinkedList()
}

/**
 * 两个非空链表代表两个非负整数，实现两数相加
 * 数字是逆序存储的：1 ->2 ->3 -> null 指的是整数 321
 *
 * 思路：数字是逆序存储的，因此直接按顺序遍历两个链表，逐个相加即可，相加的过程中记录好进位值
 *
 * https://leetcode.cn/problems/add-two-numbers/description/
 */
fun addTwoNumbers(head1: Node<Int>?, head2: Node<Int>?): Node<Int>? {
    val dummyHead = Node(0)
    var cur = dummyHead
    var carry = 0

    var cur1 = head1
    var cur2 = head2
    while (cur1 != null || cur2 != null) {
        val v1 = cur1?.value ?: 0
        val v2 = cur2?.value ?: 0
        val sum = v1 + v2 + carry
        carry = sum / 10
        cur.next = Node(sum % 10)
        cur = cur.next!!

        if (cur1 != null) cur1 = cur1.next
        if (cur2 != null) cur2 = cur2.next
    }

    if (carry > 0) {
        cur.next = Node(carry)
    }

    return dummyHead.next
}
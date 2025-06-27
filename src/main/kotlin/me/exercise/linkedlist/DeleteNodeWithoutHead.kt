package me.exercise.linkedlist

fun main() {
    val list = Node.getLinkedList()
    println("before")
    list.printLinkedList()
    println("after")
    val target = list.next?.next
    deleteTargetWithoutHead(target)
    list.printLinkedList()
}

/**
 * 不给头结点，删除给定结点
 *
 * 思路：将后一个结点的值赋给待删除的节点，然后待删除的结点指向next.next, 绕过了 next 结点，造成了删除的“假象”
 * 本质是值覆盖，并没有处理结点的引用
 * 无法处理尾结点
 *
 * https://leetcode.cn/problems/delete-node-in-a-linked-list/description/
 */
fun <T> deleteTargetWithoutHead(target: Node<T>?) {
    if (target == null || target.next == null) return
    val next = target.next
    target.value = next!!.value
    target.next = next.next
}

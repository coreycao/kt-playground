package me.exercise.linkedlist

fun main() {
    // false
    val list = Node.getLinkedList()
    println(isLinkedListHasRing(list))

    // false
    val list2 = Node.emptyLinkedList<Int>()
    println(isLinkedListHasRing(list2))

    // true
    val list3 = Node.getLinkedListWithRing()
    println(isLinkedListHasRing(list3))
}

/**
 * 判断链表中是否存在环
 *
 * 快慢指针法
 */
fun <T> isLinkedListHasRing(head: Node<T>?): Boolean {
    var fast = head
    var slow = head

    // 循环条件同时做好了 head 的边界判断
    while (fast != null && fast.next != null) {
        slow = slow?.next
        fast = fast.next?.next
        if (fast == slow) {
            return true
        }
    }
    return false
}
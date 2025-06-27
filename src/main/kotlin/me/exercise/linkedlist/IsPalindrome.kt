package me.exercise.linkedlist

/**
 * 判断链表是否回文
 *
 * 思路：使用一个辅助栈
 */
fun <T : Comparable<T>> isPalindrome(head: Node<T>?): Boolean {
    if (head == null) return false

    val stack = ArrayDeque<T>()
    var current = head
    while (current != null) {
        stack.addFirst(current.value)
        current = current.next
    }

    current = head
    var result = true
    while (current != null) {
        if (current.value != stack.removeFirst()) {
            result = false
            break
        }
        current = current.next
    }
    return result
}

fun main() {
    // false
    println(isPalindrome(Node.getLinkedList()))

    // false
    println(isPalindrome(Node.emptyLinkedList<Int>()))

    // true
    println(isPalindrome(Node.singleNodeLinkedList(1)))

    // true
    println(isPalindrome(Node.getPalindromeLinkedList()))
}
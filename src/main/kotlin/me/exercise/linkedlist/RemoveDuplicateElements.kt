package me.exercise.linkedlist

fun main() {
    val list = Node.getLinkedListWithDuplicateElements()
    println("before")
    list.printLinkedList()
    println("\nafter")
    removeDuplicate(list).printLinkedList()

    println("\n Palindrome list")
    removeDuplicate(Node.getPalindromeLinkedList()).printLinkedList()
}

/**
 * 利用一个 Set 做辅助来判断元素是否重复
 */
fun <T> removeDuplicate(head: Node<T>?): Node<T>? {
    if (head == null || head.next == null) return head

    val set = mutableSetOf<T>(head.value)

    var current = head.next
    var previous = head
    while (current != null) {
        if (set.contains(current.value)) {
            // remove node
            previous!!.next = current.next
        } else {
            set.add(current.value)
            previous = current
        }
        current = current.next
    }
    return head
}
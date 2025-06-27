package me.exercise.linkedlist

fun main() {
    val list1 = arrayOf(1, 2, 3, 4, 5).toLinkedList()

    val list2 = arrayOf(3, 4, 5, 6, 7).toLinkedList()

    val list3 = arrayOf(8, 9, 10).toLinkedList()

    // 3,4,5
    printCommonPartOfOrderedList(list1, list2)

    // noting
    printCommonPartOfOrderedList(list1, list3)
}

/**
 * 打印两个有序链表的公共部分
 */
fun <T : Comparable<T>> printCommonPartOfOrderedList(head1: Node<T>?, head2: Node<T>?) {
    var current1 = head1
    var current2 = head2
    while (current1 != null && current2 != null) {
        // 小的向后移动，相等则打印
        if (current1.value < current2.value) {
            current1 = current1.next
        } else if (current1.value > current2.value) {
            current2 = current2.next
        } else {
            print("${current1.value} ")
            current1 = current1.next
            current2 = current2.next
        }
    }
}
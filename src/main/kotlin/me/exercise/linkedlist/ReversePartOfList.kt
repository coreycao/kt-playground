package me.exercise.linkedlist


fun main() {
    val list = arrayOf(1, 2, 3, 4, 5).toLinkedList()
    reverseBetween(list, 2, 3).printLinkedList()
}

/**
 * 反转部分链表
 *
 * 思路：
 * 定位 m 和 n 两个位置，断开中间链表，反转，接回去
 */
fun <T> reverseBetween(head: Node<T>?, m: Int, n: Int): Node<T>? {
    if (head == null) {
        return head
    }

    val dummyHead = Node.singleNodeLinkedList(head.value)
    dummyHead.next = head

    var mCur: Node<T>? = dummyHead
    for (i in 1 until m) {
        // mCur 指向第 m 个节点前面的位置
        mCur = mCur?.next
    }

    var nCur: Node<T>? = dummyHead
    for (i in 0 until n) {
        // nCur 指向第 n 个结点
        nCur = nCur?.next
    }

    // 断开中间链表: subHead 指向中间链表头部，tail 指向剩余部分头部
    val subHead = mCur?.next
    val restHead = nCur?.next
    // 断开连接
    mCur?.next = null
    nCur?.next = null

    // 反转中间链表
    // 反转后 subHead 成为中间链表的尾节点
    val reversedSub = reverse(subHead)

    // 接回去
    mCur?.next = reversedSub
    subHead?.next = restHead

    return dummyHead.next
}
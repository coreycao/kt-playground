package me.exercise.stack

interface CustomStack<T> {
    fun push(item: T): Boolean
    fun pop(): T?
    fun size(): Int
    fun isEmpty(): Boolean {
        return size() == 0
    }
}

/**
 * 顺序栈
 *
 * 使用数组实现的顺序栈，容量初始化后不可变更
 */
class ArrayStack<T>(private val capacity: Int = 0) : CustomStack<T> {

    private val stack: Array<T?> by lazy {
        arrayOfNulls(capacity)
    }

    // 相当于一个指向栈顶的指针
    private var count: Int = 0

    override fun push(item: T): Boolean {
        if (count == capacity) return false
        stack[count] = item
        count++
        return true
    }

    override fun pop(): T? {
        if (count == 0) return null
        val result = stack[count - 1]
        count--
        return result
    }

    override fun size() = count

}

/**
 * 使用数组实现的顺序栈，容量满后支持动态扩容
 */
class DynamicArrayStack<T>(private val initCapacity: Int = 0) : CustomStack<T> {
    private var stack: Array<T?> = arrayOfNulls(initCapacity)

    // 相当于一个指向栈顶的指针
    private var count: Int = 0

    override fun push(item: T): Boolean {
        if (count == stack.size) {
            // 此处选择的扩容策略是直接翻倍，不同的数据结构在实际设计时会有不同的扩容策略
            val resizedStack: Array<T?> = arrayOfNulls(stack.size * 2)
            stack.copyInto(resizedStack)
            stack = resizedStack
        }
        stack[count] = item
        count++
        return true
    }

    override fun pop(): T? {
        if (count == 0) return null
        else {
            count--
            return stack[count]
        }
    }

    override fun size() = count

}

class Node<T>(var value: T?, var next: Node<T>? = null)

/**
 * 链式栈
 *
 * 出栈和入栈操作都在头结点处
 * 使用一个 dummyHead 以简化操作
 */
class LinkedStack<T> : CustomStack<T> {
    private val dummyHead: Node<T> = Node(null)

    override fun push(item: T): Boolean {
        val node = Node<T>(item, null)
        node.next = dummyHead.next
        dummyHead.next = node
        return true
    }

    override fun pop(): T? {
        if (dummyHead.next == null) return null
        val node = dummyHead.next
        dummyHead.next = node?.next
        return node?.value
    }

    override fun size(): Int {
        var count = 0
        var cursor = dummyHead
        while (cursor.next != null) {
            count++
            cursor = cursor.next!!
        }
        return count
    }

    override fun isEmpty(): Boolean = dummyHead.next == null
}


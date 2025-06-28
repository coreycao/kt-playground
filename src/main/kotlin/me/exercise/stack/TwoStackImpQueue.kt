package me.exercise.stack

/**
 * 使用两个栈实现队列
 */
class DoubleStackQueue<T> {
    private val inStack by lazy { ArrayDeque<T>() }
    private val outStack by lazy { ArrayDeque<T>() }

    fun enqueue(t: T) {
        inStack.addFirst(t)
    }

    // 出队时，直接从 outStack 中弹出元素，如果 outStack 为空，那么将 inStack 全部弹出并压入 outStack
    fun dequeue(): T {
        if (outStack.isEmpty()) {
            while (inStack.isNotEmpty()) {
                outStack.addFirst(inStack.removeFirst())
            }
        }
        return outStack.removeFirst()
    }

    fun isEmpty(): Boolean {
        return inStack.isEmpty() && outStack.isEmpty()
    }
}

fun main() {
    val queue = DoubleStackQueue<Int>().apply {
        enqueue(1)
        enqueue(2)
        enqueue(3)
        enqueue(4)
        enqueue(5)
        enqueue(6)
    }

    while (queue.isEmpty().not()) {
        print("${queue.dequeue()} ")
    }
}
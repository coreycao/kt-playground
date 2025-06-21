package me.exercise.stack

import java.util.*

/**
 * 使用一个辅助栈对一个已知栈的元素进行排序
 *
 * 思路：
 * rawStack 弹出一个元素 a，与 helperStack 的顶部元素比较，满足比较条件则push 到 helper 中，
 * 否则 helper 逐个出栈直到满足比较条件，将 a 压入
 * 重复以上过程，直到 rawStack 为空，此时 helperStack 元素已成逆序排列，逐个 pop 并 push 到 rawStack 中即可
 *
 * 边界条件：helperStack 为空时直接 push 进去；rawStack 为空时排序完成
 */
fun <T : Comparable<T>> sortStackByStack(rawStack: Stack<T>, ascend: Boolean = true) {

    val helperStack = Stack<T>()

    while (rawStack.isNotEmpty()) {
        // 从 rawStack 弹出顶部元素
        val cur = rawStack.pop()

        //  站在 HelperStack 的视角：helperStack 不为空且 helperStack 顶部元素大于 cur
        //  那么将 helper的元素不断弹出，直到元素不大于 cur 或者helperStack 为空，此时找到了 cur 应该正确放置的位置，

        while (helperStack.isNotEmpty() && if (ascend) cur > helperStack.peek() else cur < helperStack.peek()) {
            rawStack.push(helperStack.pop())
        }

        // 经过上述循环，helperStack 顶部就是 cur 应该放置的正确位置，将其压入
        helperStack.push(cur)
    }

    // 将排序好的元素 push 到原来的栈中
    while (helperStack.isNotEmpty()) {
        rawStack.push(helperStack.pop())
    }
}

fun main() {
    val rawStack = Stack<Int>().apply {
        push(4)
        push(5)
        push(2)
        push(6)
        push(1)
        push(3)
    }

    sortStackByStack(rawStack, false)

    rawStack.forEach {
        print("$it ")
    }
}
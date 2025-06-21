package me.exercise.stack

/**
 * 设计一个栈，除了栈的pup, pop, isEmpty等常规操作外，还有一个 min 方法，能够在常数时间内返回栈的最小元素。
 *
 * 关联的 LeetCode 题目
 * https://leetcode.cn/problems/min-stack/description/
 *
 * 思路：维护两个栈，一个用来存放所有原始数据，另一个用来维护最小值
 */
class MinStack<T : Comparable<T>> {

    private val rawStack by lazy { ArrayDeque<T>() }

    private val minStack by lazy { ArrayDeque<T>() }

    fun push(t: T) {
        rawStack.addFirst(t)
        if (minStack.isEmpty) {
            minStack.addFirst(t)
        } else if (min() != null && t <= min()!!) {
            minStack.addFirst(t)
        }
    }

    fun pop(): T {
        val t = rawStack.removeFirst()
        if (t == min()) {
            minStack.removeFirst()
        }
        return t
    }

    fun isEmpty(): Boolean {
        return rawStack.isEmpty
    }

    fun min(): T? {
        if (isEmpty()) {
            return null
        }
        return minStack.first()
    }
}

fun main() {
    val minStack = MinStack<Int>()
    minStack.push(3)
    minStack.push(4)
    minStack.push(5)
    minStack.push(1)
    minStack.push(2)
    minStack.push(1)

    // 1
    println(minStack.min())

    minStack.pop()
    minStack.pop()
    //1
    println(minStack.min())

    minStack.pop()
    //3
    println(minStack.min())
}
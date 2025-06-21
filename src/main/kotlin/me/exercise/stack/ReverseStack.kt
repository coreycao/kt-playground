package me.exercise.stack

/**
 * 递归一：移除栈底元素
 *
 * 在递归调用的过程中，将中间元素“暂存”在了函数调用栈中，直至到达递归出口，拿到了栈底元素
 * 递归回溯的过程中，将“暂存”的中间元素又原序放回了栈中
 */
fun <E> getAndRemoveLast(stack: ArrayDeque<E>): E {
    val ret = stack.removeFirst()

    // 递归出口：到了栈底
    if (stack.isEmpty()) {
        return ret
    } else {
        val last = getAndRemoveLast(stack)
        // 放回暂存的中间元素
        stack.addFirst(ret)
        return last
    }
}

fun <T> reverseStackByRecursion(stack: ArrayDeque<T>) {
    if (stack.isEmpty){
        return
    }
    // 递归二：不断递归地拿到栈底元素，此时栈内元素全部暂存于 reverse 函数的调用栈中，递归回溯的过程中将“暂存”的栈底元素 push 到原栈中，实现逆序
    val last = getAndRemoveLast(stack)
    reverseStackByRecursion(stack)
    stack.addFirst(last)
}

fun main() {

    // 栈底为 1
    val stack = ArrayDeque<Int>().apply {
        addFirst(1)
        addFirst(2)
        addFirst(3)
        addFirst(4)
        addFirst(5)
        addFirst(6)
    }

    // 逆序后栈底为 6
    reverseStackByRecursion(stack)

    while (stack.isNotEmpty()){
        print("${stack.removeFirst()}  ")
    }

}
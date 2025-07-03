package me.exercise.dynamicplan

/**
 * 共 n 阶楼梯，每次走一步或两步，爬到顶部共多少种走法
 *
 * 递推公式
 * f(n) = f(n-1) + f(n-2)
 * ...
 * f(2) = f(1) + f(0), f(1) = 1, f(0) = 1
 *
 * 递归法
 * @see me.exercise.recursion.climbRecursion
 */
fun climb(n: Int): Int {
    if (n == 0 || n == 1) {
        return 1
    }

    val result = IntArray(n + 1)
    result[0] = 1
    result[1] = 1
    for (i in 2..n) {
        result[i] = result[i - 1] + result[i - 2]
    }
    return result[n]
}
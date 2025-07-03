package me.exercise.recursion

/**
 * 共 n 阶楼梯，每次走一步或两步，爬到顶部共多少种走法
 *
 * 递推公式
 * f(n) = f(n-1) + f(n-2)
 * ...
 * f(2) = f(1) + f(0), f(1) = 1, f(0) = 1
 */
fun climbRecursion(n: Int): Int {
    if (n == 0 || n == 1) {
        return 1
    }

    return climbRecursion(n - 1) + climbRecursion(n - 2)
}

package me.exercise.recursion

/**
 * 斐波那契数列
 *
 * f(n) = f(n-1) + f(n-2)
 */
fun fib(n: Int): Int {
    if (n == 0) return 0
    if (n == 1) return 1
    return fib(n - 1) + fib(n - 2)
}
package me.exercise.leetcode

import kotlin.math.abs

/**
 * https://leetcode.cn/problems/divide-two-integers/
 */
fun divide(dividend: Int, divisor: Int): Int {
    val negative = divisor < 0 && dividend > 0 || divisor > 0 && dividend < 0
    var a = abs(dividend)
    var b = abs(divisor)

    var result = 0
    while ((a - b) >= 0) {
        result++
        a -= b
    }
    return if (negative) -result else result
}

fun main() {
    println(divide(5, 2))
    println(divide(11, -1))
    println(divide(2, -3))
}
package me.exercise.stringmatch

/**
 * Brute Force 朴素匹配算法，或叫暴力匹配算法
 * 如其名字所示，是一种简单直接的匹配算法，在主串中逐渐向后“滑动”来检查是否存在匹配的模式串
 *
 * 算法时间复杂度较高，O(n*m)，n 是主串长度，m 是模式串的长度
 * 优点是实现简单，不易出错
 */
fun strStrBF(source: String, pattern: String): Int {
    val n = source.length
    val m = pattern.length
    for (i in 0..n - m) {
        var flag = true
        for (j in 0 until m) {
            if (source[i + j] != pattern[j]) {
                flag = false
                break
            }
        }
        if (flag) {
            return i
        }
    }
    return -1
}

fun main() {
    // 3
    println(
        strStrBF("hello world", "lo")
    )

    // -1
    println(
        strStrBF("hello world", "word")
    )
}
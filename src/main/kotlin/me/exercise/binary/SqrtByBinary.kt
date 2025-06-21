package me.exercise.binary

import kotlin.math.sqrt

/**
 * 使用二分法求平方根
 *
 * 思路：不断二分，中间值的平方大于目标值时，取左区间，否则取右区间
 *
 */
fun sqrtByBinary(target: Double, precision: Double = 0.0001): Double {
    var low = 0.0
    var high = target
    var mid = (low + high) / 2
    while ((high - low) > precision) {
        mid = (low + high) / 2
        if (mid * mid < target) {
            low = mid
        } else {
            high = mid
        }
    }
    return (low + high) / 2
}

fun main(){
    val x = 2.33
    println("Kotlin#sqrt ${sqrt(x)}")
    println("sqrtByBinary ${sqrtByBinary(x)}")
}
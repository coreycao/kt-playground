package me.exercise.bit

/**
 * 使用异或运算交换 A 和 B
 *
 * a = a ^ b; // 第一步：存储差异信息
 * b = a ^ b; // 第二步：还原原始a值到b
 * a = a ^ b; // 第三步：还原原始b值到a
 */
fun bitExchange(a: Int, b: Int){
    var x = a
    var y = b
    println("before: a: $x, b: $y")
    x = x.xor(y)
    y = x.xor(y)
    x = x.xor(y)
    println("after: a: $x, b: $y")
}

fun main() {
    bitExchange(12,13)
}
package me.exercise.concurrent.thread

import java.util.concurrent.CountDownLatch

/**
 * 五个线程，先一起打印 hello，后一起打印 world
 * 可以使用 CountDownLatch / CyclicBarrier 来实现.
 */

fun main() {
    val latch = CountDownLatch(5)
    val threads = List(5) {
        Thread {
            runCatching {
                println("${Thread.currentThread().name}: hello")
                latch.countDown()
                latch.await()
                println("${Thread.currentThread().name}: world")
            }
        }
    }

    threads.forEach { it.start() }
}
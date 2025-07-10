package me.exercise.concurrent.thread

import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

/**
 * 两个线程同时运行，交替打印出 1 0 1 0
 * <p>
 * 此处使用了两种方式来实现.
 * 1) synchronized
 * 2) Reentrant lock, 配合 condition
 *    它可以实现与 synchronized 同样的语义. 因此二者在实现上等价的.
 */
fun main() {
    // twoThreadSync()
    twoThreadLock()
}

class PrintTask(val num: Int) : Runnable {
    companion object {
        val lock = Object()
    }

    override fun run() {
        while (true) {
            synchronized(lock) {
                runCatching {
                    println("${Thread.currentThread().name}: print $num")
                    Thread.sleep(1000)
                    lock.notifyAll()
                    lock.wait()
                }
            }
        }
    }
}

// 使用 synchronized 同步来实现两个线程交替运行
fun twoThreadSync() {
    Thread(PrintTask(0)).start()
    Thread(PrintTask(1)).start()
}

fun twoThreadLock() {
    val lock = ReentrantLock()
    val conditionA = lock.newCondition()
    val conditionB = lock.newCondition()

    val threadA = Thread {
        while (true) {
            lock.withLock {
                println("${Thread.currentThread().name}: print $0")
                Thread.sleep(1000)
                conditionB.signal()
                conditionA.await()
            }
        }
    }

    val threadB = Thread {
        while (true) {
            lock.withLock {
                println("${Thread.currentThread().name}: print $1")
                Thread.sleep(1000)
                conditionA.signal()
                conditionB.await()
            }
        }
    }

    threadA.start()
    threadB.start()
}
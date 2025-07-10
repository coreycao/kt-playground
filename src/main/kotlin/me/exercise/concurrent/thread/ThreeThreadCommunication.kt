package me.exercise.concurrent.thread

import kotlinx.coroutines.Runnable
import java.util.concurrent.locks.Condition
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

/**
 * 三条线程, 交替运行, 打印 0, 1, 2
 */
fun main() {
    val condition1 = LinkedTask.lock.newCondition()
    val condition2 = LinkedTask.lock.newCondition()
    val condition3 = LinkedTask.lock.newCondition()

    Thread(LinkedTask(0, condition1, condition2)).start()
    Thread(LinkedTask(1, condition2, condition3)).start()
    Thread(LinkedTask(2, condition3, condition1)).start()
}

class LinkedTask(val num: Int, val cur: Condition, val next: Condition) : Runnable {

    companion object {
        val lock = ReentrantLock()
    }

    override fun run() {
        while (true){
            lock.withLock {
                println("${Thread.currentThread().name}: print $num")
                Thread.sleep(1000)
                next.signal()
                cur.await()
            }
        }
    }
}
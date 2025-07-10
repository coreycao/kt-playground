package me.exercise.concurrent.thread

import kotlinx.coroutines.Runnable

private val lock = Object()

private var target = 0

fun isFull() = target == 10

fun isEmpty() = target == 0

class Producer(val name: String) : Runnable {
    override fun run() {
        while (true) {
            synchronized(lock) {
                while (isFull()){
                    println("FULL, $name waiting")
                    lock.wait()
                }
                target++
                println("$name produce: $target")
                lock.notifyAll()
            }
        }
    }
}

class Consumer(val name: String): Runnable {
    override fun run() {
        while (true){
            synchronized(lock){
                while (isEmpty()){
                    println("EMPTY, $name waiting")
                    lock.wait()
                }
                target--
                println("$name consume: $target")
                lock.notifyAll()
            }
        }
    }
}

fun main() {
    Thread(Producer("P1")).start()
    Thread(Producer("P2")).start()
    Thread(Producer("P3")).start()

    Thread(Consumer("C1")).start()
    Thread(Consumer("C2")).start()
    Thread(Consumer("C3")).start()
    Thread(Consumer("C4")).start()
}
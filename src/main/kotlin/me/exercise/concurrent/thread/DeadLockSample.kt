package me.exercise.concurrent.thread

/**
 * 两个线程互相持有对方的锁，因竞争状态形成死锁
 */
fun main() {
    val lock1 = Object()
    val lock2 = Object()

    Thread {
        synchronized(lock1){
            println("Task 1 got lock1")
            Thread.sleep(500)
            synchronized(lock2){
                println("Task 1 got lock2")
            }
        }
    }.start()

    Thread {
        synchronized(lock2){
            println("Task 2 got lock2")
            synchronized(lock1){
                println("Task 2 got lock1")
            }
        }
    }.start()

}
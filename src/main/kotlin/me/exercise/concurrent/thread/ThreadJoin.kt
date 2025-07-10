package me.exercise.concurrent.thread

val threadA = Thread {
    repeat(5) {
        println("${Thread.currentThread().name} $it")
        Thread.sleep(1000)
    }
}

val threadB = Thread {
    repeat(5) {
        // 等待 threadA 完成
        threadA.join()
        println("${Thread.currentThread().name} $it")
        Thread.sleep(1000)
    }
}

fun main() {
    threadA.start()
    threadB.start()
}
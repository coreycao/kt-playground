package me.exercise.concurrent.coroutine

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        twoCoroutineCommunicate()
    }
}

/**
 * 两个协程交替运行，一个打印 A，一个打印 B
 */
suspend fun twoCoroutineCommunicate() {

    val channel = Channel<Unit>()

    val repeatCount = 10

    coroutineScope {
        val jobA = launch {
            repeat(repeatCount) {
                delay(500)
                println("CoroutineA on ${Thread.currentThread()}: A")
                // send signal to coroutineB
                channel.send(Unit)
                // suspend to wait for coroutineB's signal
                channel.receive()
            }
        }

        val jobB = launch {
            repeat(repeatCount) {
                // once receive a signal from CoroutineA, print B
                channel.receive()
                delay(500)
                println("CoroutineB on ${Thread.currentThread()}: B")
                // send signal to Coroutine A
                channel.send(Unit)

            }
        }
    }

    channel.close()
}
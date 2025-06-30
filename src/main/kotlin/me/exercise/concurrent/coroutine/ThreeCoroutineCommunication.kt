package me.exercise.concurrent.coroutine

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel

fun main() = runBlocking {
    threeCoroutineCommunication()
}

suspend fun operateElement(
    name: String,
    element: MutableList<Int>,
    incomingChannel: Channel<Unit>,
    outgoingChannel: Channel<Unit>
) {
    repeat(10) {
        incomingChannel.receive()
        delay(200)
        element[0] = element[0] + 1
        println("$name: element: ${element[0]}")
        outgoingChannel.send(Unit)
    }
}

/**
 * 三个协程按照严格的顺序依次访问并操作一个共享的元素
 */
suspend fun threeCoroutineCommunication() {
    val job = Job()
    val scope = CoroutineScope(Dispatchers.Default + job)

    // a mutable common shared element
    val counter = mutableListOf(0)

    val channelA = Channel<Unit>()
    val channelB = Channel<Unit>()
    val channelC = Channel<Unit>()

    val coroutineA = scope.launch {
        operateElement("CoroutineA", counter, channelA, channelB)
    }

    val coroutineB = scope.launch {
        operateElement("CoroutineB", counter, channelB, channelC)
    }

    val coroutineC = scope.launch {
        operateElement("CoroutineC", counter, channelC, channelA)
    }

    // active coroutineA to start
    channelA.send(Unit)

    // delay to  wait all coroutine finished
    delay(10000)

    // cancel all coroutine
    job.cancel()

    println("threeCoroutineCommunication#end")

}


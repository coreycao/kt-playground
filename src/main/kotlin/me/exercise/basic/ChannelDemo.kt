package me.exercise.basic

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce


// Channel Demo from official doc
// basic use
suspend fun channelDemo1() {
    coroutineScope {
        val channel = Channel<Int>()
        launch {
            for (x in 1..5) channel.send(x)
        }

        repeat(5) {
            channel.receive().also { println(it * it) }
        }
        println("Done")
    }
}

// close channel
suspend fun channelDemo2() {
    coroutineScope {
        val channel = Channel<Int>()
        launch {
            for (x in 1..5) channel.send(x)
            // close the channel
            channel.close()
        }

        for (x in channel) {
            println(x)
        }
        println("Done")
    }
}

@OptIn(ExperimentalCoroutinesApi::class)
suspend fun channelDemo3() {
    // produce and consumeEach
    fun CoroutineScope.produceSquares(): ReceiveChannel<Int> = produce<Int>{
        for (i in 1..5) {
            send(i * i)
        }
    }

    coroutineScope {
        val squares = produceSquares()
        squares.consumeEach {
            println(it)
        }
        println("done")
    }
}

suspend fun channelDemo4() {
    // procure numbers infinitely
    @OptIn(ExperimentalCoroutinesApi::class)
    fun CoroutineScope.produceNumbers() = produce<Int> {
        var x = 1
        while (true) {
            // println("worker ${Thread.currentThread()}, send number: $x")
            send(x++)
        }
    }

    // a channel than received numbers and send their squares
    @OptIn(ExperimentalCoroutinesApi::class)
    fun CoroutineScope.square(numbers: ReceiveChannel<Int>) = produce<Int> {
        for (x in numbers) {
            // println("worker ${Thread.currentThread()}, send square of : $x")
            send(x * x)
        }
    }

    coroutineScope {
        val numbers = produceNumbers()
        val squares = square(numbers)
        repeat(5) {
            squares.receive().also { println(it) }
        }
        println("Done")

        // cancel its children coroutines
        coroutineContext.cancelChildren()
    }
}

suspend fun channelDemo5() {

    @OptIn(ExperimentalCoroutinesApi::class)
    fun CoroutineScope.produceNumbersStartsWith(start: Int) = produce<Int> {
        var x = start
        while (true) {
            // println("worker ${Thread.currentThread()}, send number: $x")
            send(x++)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun CoroutineScope.filterPrime(numbers: ReceiveChannel<Int>, prime: Int) = produce<Int> {
        for (x in numbers) {
            if (x % prime != 0) {
                // println("worker ${Thread.currentThread()}filterPrime: $x")
                send(x)
            }
        }
    }

    coroutineScope {
        var cur = produceNumbersStartsWith(2)
        repeat(10) {
            val prime = cur.receive()
            println(prime)
            cur = filterPrime(cur, prime)
        }
        println("Done")
        coroutineContext.cancelChildren()
    }
}

// fan-out: single producer to multi consumers
suspend fun channelDemo6() {
    fun CoroutineScope.produceNumbers() = produce<Int> {
        var x = 1 // start from 1
        while (true) {
            send(x++) // 产生下一个数字
            delay(100) // 等待 0.1 秒
        }
    }

    fun CoroutineScope.launchProcessor(id: Int, channel: ReceiveChannel<Int>) = launch {
        for (msg in channel) {
            println("Processor #$id received $msg")
        }
    }

    coroutineScope {
        val producer = produceNumbers()
        repeat(5) { launchProcessor(it, producer) }
        delay(950)
        producer.cancel() // 取消协程生产者从而将它们全部杀死
    }
}

// fan-in: multi producers to single consumer, similar to Merge.
suspend fun channelDemo7() {
    suspend fun sendString(channel: SendChannel<String>, s: String, time: Long) {
        while (true) {
            delay(time)
            channel.send(s)
        }
    }

    coroutineScope {
        val channel = Channel<String>()
        launch { sendString(channel, "foo", 200L) }
        launch { sendString(channel, "BAR!", 500L) }
        repeat(6) { // 接收前六个
            println(channel.receive())
        }
        coroutineContext.cancelChildren() // 取消所有子协程来让主协程结束
    }
}
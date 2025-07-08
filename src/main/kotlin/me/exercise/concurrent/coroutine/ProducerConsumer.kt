package me.exercise.concurrent.coroutine

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.concurrent.atomics.AtomicInt
import kotlin.concurrent.atomics.ExperimentalAtomicApi
import kotlin.random.Random

@OptIn(ExperimentalAtomicApi::class)
fun main() = runBlocking {
    val capacity = 5
    val channel = Channel<Int>(
        // capacity of the producer
        capacity,
        // action on buffer overflow, BufferOverflow.SUSPEND is Channel's Default setting
        onBufferOverflow = BufferOverflow.SUSPEND
    )

    // data class Product(val producerName: String, val productIndex: Int)

    val productIndex = AtomicInt(0)

    // launch producer Coroutine
    val producerJobs = List(2) { index ->
        launch {
            while (true) {
                // producer will suspend if channel is full
                channel.send(productIndex.load()).also {
                    println("Producer$index#produce: ${productIndex.addAndFetch(1)}")
                }
                delay(Random.nextLong(1000, 3000))
            }
        }
    }

    // launch Consumer Coroutine
    val consumerJobs = List(3) { index ->
        launch {
            // consumer will suspend if channel is empty
            for (item in channel) {
                println("Consumer$index#consume: $item")
                delay(Random.nextLong(2000, 5000))
            }
        }
    }

    delay(30_000)

    producerJobs.forEach { job ->
        job.cancel()
    }
    consumerJobs.forEach { job ->
        job.cancel()
    }

    channel.close()

    println("end")
}

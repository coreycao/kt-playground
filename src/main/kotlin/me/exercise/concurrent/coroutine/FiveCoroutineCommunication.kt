package me.exercise.concurrent.coroutine

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.yield

fun main() {
    runBlocking {
        fiveCoroutineCommunication()
    }
}

suspend fun fiveCoroutineCommunication(){
    coroutineScope {
        val jobs = List(5) { index ->
            launch(context= CoroutineName("Coroutine#$index")) {
                println("${coroutineContext[CoroutineName]?.name}: Hello")
                yield()
                println("${coroutineContext[CoroutineName]?.name}: World")
            }
        }
    }
}
package me.exercise.basic

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

// SharedFlow is hot flow, can be used as event bus
class EventBus {
    private val _events = MutableSharedFlow<String>()
    val events = _events.asSharedFlow()

    suspend fun postEvent(event: String) {
        _events.emit(event)
    }
}

suspend fun main() {
    val eventBus = EventBus()

    GlobalScope.launch {
        println("Subscriber#1 start collect...")
        eventBus.events.collect { event ->
            println("Subscriber#1: $event")
        }
    }

    delay(100)

    println("Post 1st event")
    eventBus.postEvent("Event 1")

    delay(100)

    GlobalScope.launch {
        println("Subscriber#2 start collect...")
        eventBus.events.collect { event ->
            println("Subscriber#2: $event")
        }
    }

    delay(100)
    println("Post 2nd event")
    eventBus.postEvent("Event 2")
}
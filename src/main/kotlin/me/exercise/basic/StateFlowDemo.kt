package me.exercise.basic

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

// StateFlow is a special SharedFlow, which holds a single value that represent its state
class TemperatureSensor {
    private val _temperature = MutableStateFlow(20.0)
    val temperature: StateFlow<Double> = _temperature.asStateFlow()

    fun updateTemperature(newTemperature: Double) {
        _temperature.update { newTemperature }
    }
}

suspend fun main() {
    val sensor = TemperatureSensor()

    GlobalScope.launch {
        sensor.temperature.collect { temperature ->
            println("Display1#Current temperature: $temperature")
        }
    }

    delay(100)

    println("update temperature to 25.0")
    sensor.updateTemperature(25.0)

    delay(100)

    GlobalScope.launch {
        println("Display2 start collect...")
        sensor.temperature.collect { temperature ->
            println("Display2#Current temperature: $temperature")
        }
    }

    delay(100)

    println("update temperature to 30.0")
    sensor.updateTemperature(30.0)
}
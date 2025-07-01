package me.exercise.design

interface Observer {
    fun onUpdate(message: String)
}

abstract class Observable {
    val observers by lazy {
        mutableListOf<Observer>()
    }

    fun addObserver(observer: Observer) {
        observers.add(observer)
    }

    fun removeObserver(observer: Observer) {
        observers.remove(observer)
    }

    fun notifyAll(message: String) {
        observers.forEach {
            it.onUpdate(message)
        }
    }
}

class Youtuber : Observable() {
    fun publishNewVideo() {
        notifyAll("new videos is coming")
    }
}

class User(val username: String) : Observer {
    override fun onUpdate(message: String) {
        println("$username get message: $message ")
    }
}

fun main() {
    val youtuber = Youtuber()

    youtuber.addObserver(User("Luffy"))
    youtuber.addObserver(User("Nami"))
    youtuber.addObserver(User("Sanji"))

    youtuber.publishNewVideo()
}

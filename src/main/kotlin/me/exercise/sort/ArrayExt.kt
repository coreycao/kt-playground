package me.exercise.sort

fun IntArray.swap(i: Int, j: Int) {
    if (i == j) return
    val tmp = this[i]
    this[i] = this[j]
    this[j] = tmp
}

fun IntArray.print() {
    this.forEachIndexed { index, i ->
        if (index == this.size - 1) {
            println(i)
        } else {
            print("$i ")
        }
    }
}

fun <T> Array<T>.swap(i: Int, j: Int) {
    val tmp = this[i]
    this[i] = this[j]
    this[j] = tmp
}

fun <T> Array<T>.print() {
    this.forEachIndexed { index, i ->
        if (index == this.size - 1) {
            println(i)
        } else {
            print("$i ")
        }
    }
}


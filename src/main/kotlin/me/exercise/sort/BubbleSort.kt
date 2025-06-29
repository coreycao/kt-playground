package me.exercise.sort

fun main() {
    val list = intArrayOf(24, 19, 26, 39, 36, 7, 31, 29, 38, 23)
    println("before: ")
    list.print()

    // bubbleSort(list)
    bubbleSortOptimized(list)
    println("after: ")
    list.print()
}

/**
 * 对冒泡排序的一个生动比喻是：打擂台。
 * 待排序元素与相邻元素比较，“获胜”则与之交换
 */
fun bubbleSort(array: IntArray) {

    if (array.isEmpty()) return

    for (i in 0 until array.size) {
        for (j in 0 until array.size - i - 1) {
            if (array[j] > array[j + 1]) {
                array.swap(j, j + 1)
            }
        }
    }
}

/**
 * 当某趟比较的过程中没有交换操作，那么说明此时数据已经有序，可以提前退出
 */
fun bubbleSortOptimized(array: IntArray){
    if (array.isEmpty()) return

    var flag = false

    for (i in 0 until array.size) {
        for (j in 0 until array.size - i - 1) {
            if (array[j] > array[j + 1]) {
                array.swap(j, j + 1)
                // 有交换操作
                flag = true
            }
        }

        // 当某趟比较的过程中没有交换操作，那么说明此时数据已经有序，可以提前退出
        if (flag.not()){
            break
        }
    }
}
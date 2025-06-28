package me.exercise.sort

fun main() {
    val array = arrayOf(26, 19, 7, 37, 27, 57, 67, 99, 87, 17)
    println("before: ")
    array.forEach { print("$it ") }
    mergeSort(array)
    println("\nafter: ")
    array.forEach { print("$it ") }
}


fun <T : Comparable<T>> mergeSort(array: Array<T>) {
    val tmp: Array<T> = array.copyOf()
    realMergeSort(array, tmp, 0, array.size - 1)
}

private fun <T : Comparable<T>> realMergeSort(array: Array<T>, tmp: Array<T>, left: Int, right: Int) {
    if (left >= right) {
        return
    }
    val mid = left + (right - left) / 2
    realMergeSort(array, tmp, left, mid)
    realMergeSort(array, tmp, mid + 1, right)
    merge(array, tmp, left, mid, right)
}

/**
 * 归并排序是分治思路的典型应用，在实现方法上也是对递归的典型应用
 *
 * 归并的核心操作：将排序好的两部分合并到一起
 *
 * 思路：
 * 申请一个临时数组来存放归并的结果
 * 使用 i 和 j 两个索引分别指向左、右两半部分的头部，
 *      array[i]<=array[j] 那么将array[i] 赋值给临时数组 tmp[k], 然后 i 和 k 向后移动
 *      array[i]>array[j] 那么将array[j] 赋值给临时数组 tmp[k], 然后 j 和 k 向后移动
 * i 或 j 移动到末尾后，处理剩余元素
 * 此时临时数组已经存放了归并的结果，再将其赋值会原本的 array
 *
 */
private fun <T : Comparable<T>> merge(array: Array<T>, tmp: Array<T>, left: Int, mid: Int, right: Int) {
    var i = left        // 左侧起点
    var j = mid + 1     // 右侧起点
    var k = left

    while (i <= mid && j <= right) {
        if (array[i] <= array[j]) {
            tmp[k++] = array[i++]
        } else {
            tmp[k++] = array[j++]
        }
    }

    while (i <= mid) {
        tmp[k++] = array[i++]
    }

    while (j <= right) {
        tmp[k++] = array[j++]
    }

    for (t in left..right) {
        array[t] = tmp[t]
    }
}
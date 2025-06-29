package me.exercise.sort

fun main() {

    val list = intArrayOf(11, 31, 12, 5, 34, 30, 26, 38, 36, 18)

    selectSort(list)

    list.print()

}

/**
 * 对于待排序元素 x，假设其左侧是排序好的序列，右侧是未排序好的序列
 * 每次从 x 及其右侧选出最小的那个值，将其放到 x 的位置
 *
 * 选择排序是不稳定排序算法：在选择出最小元素与前面的元素交换位置时，会造成相等元素的相对位置错乱
 * 如：5a 8 5b 2 9 序列中，5a 与 2 交换位置后，5a 与 5b 的相对位置错乱
 *
 * 选择排序的交换思想与快速排序的“划分”操作有相似之处
 */
fun selectSort(array: IntArray) {

    if (array.isEmpty()) return

    for (i in 0 until array.size) {

        var targetIndex = i

        for (j in i + 1 until array.size) {
            if (array[j] < array[targetIndex]) {
                targetIndex = j
            }
        }
        array.swap(i, targetIndex)
    }
}
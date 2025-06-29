package me.exercise.sort

fun main() {
    val list = intArrayOf(11, 31, 12, 5, 34, 30, 26, 38, 36, 18)

    insertSort(list)

    list.print()
}

/**
 * 插入排序的思想是：
 *  待排序元素 a 的左侧为已经有序的数据，右侧为待排序的数据
 *  所谓插入是从左侧寻找 a 合适的位置将其插入
 *  具体执行方式是：比 a 大的元素向右移动以腾出插入空间，直至找到插入位置后将 a 插入
 */
fun insertSort(array: IntArray) {
    if (array.isEmpty()) return
    for (i in 1 until array.size) {
        // 待排序元素的值
        val target = array[i]

        var j = i - 1

        while (j >= 0) {
            if (array[j] > target) {
                array[j + 1] = array[j]
                j--
            } else {
                break
            }
        }

        println("insert $target to index: ${j + 1}")

        array[j + 1] = target
    }

}
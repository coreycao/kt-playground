package me.exercise.sort

fun main() {
    val array = arrayOf(26, 19, 7, 37, 27, 57, 67, 99, 87, 17)

    println("before: ")
    array.forEach {
        print("$it ")
    }
    println("\nafter: ")
    quickSort(array)
    array.forEach {
        print("$it ")
    }
}

/**
 * 快速排序与归并排序的对比
 *
 * 二者都是分治法的典型代表
 *
 * 归并排序逐层递归至“底部”，实现最小部分的归并后则逐层向上继续完成所谓的“归并”
 *
 * 快速排序从一开始便通过巧妙的 partition 函数实现了划分，并且不断地划分，直至完成所有元素的排列
 * 写法上虽然也有递归，但是在递归栈返回的过程中，并不再进行额外的处理
 *
 * 此外，归并排序由于其“归并”操作使用了一个额外的空间，因此并不是原地排序
 * 而快速排序由于在于 pivot 对比交换的过程中会导致元素的顺序混乱，因此是不稳定排序
 */
fun <T : Comparable<T>> quickSort(array: Array<T>) {
    realQuickSort(array, 0, array.size - 1)
}

private fun <T : Comparable<T>> realQuickSort(array: Array<T>, left: Int, right: Int) {
    if (left >= right) return
    val pivotIndex = partition(array, left, right)
    realQuickSort(array, left, pivotIndex - 1)
    realQuickSort(array, pivotIndex + 1, right)
}

/**
 * partition 函数是快速排序算法的核心
 * 它的作用是选定一个标准值，将大于和小于标准值的元素分别划分为两部分
 */
private fun <T : Comparable<T>> partition(array: Array<T>, left: Int, right: Int): Int {
    // 选定最右侧的值作为标准值
    val pivot = array[right]

    // i 作为一个指针，从逻辑上将数据划分为已处理和未处理两个部分
    // 并且 i 始终指向 array 中被划分好的那部分数据的末尾
    var i = left

    // j 仅仅是迭代器
    // 如果迭代到的元素小于标准值 pivot，那么将其放到已处理区间的末尾，也就是 i 的位置上
    for (j in left until right) {
        if (array[j] < pivot) {
            var tmp = array[i]
            array[i] = array[j]
            array[j] = tmp
            i++
        }
    }

    // 此时，left 到 i-1 的元素小于 pivot, i + 1 到 right 的元素大于 pivot
    // 将 pivot 放到 i 的位置上，那么, left 到 right 间的元素便完成了划分
    // i 所在的位置即为划分点的位置

    array[right] = array[i]
    array[i] = pivot
    return i
}
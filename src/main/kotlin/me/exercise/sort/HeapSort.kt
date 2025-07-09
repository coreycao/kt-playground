package me.exercise.sort

/**
 * 堆的基本操作：
 *  @see me.exercise.tree.Heap
 *
 * 堆排序：先建堆，再排序
 * 是原地排序，但不稳定
 *
 * 建堆有两种方式：
 * 1) 自下而上地堆化
 * 2）自上而下地堆化
 */

/**
 * 自下而上地堆化:
 *   - 将数组的元素逐个插入到第一个位置，并执行堆化
 *   - 与向堆中插入元素的操作类似，是一种自下而上堆化的方式
 *  @see me.exercise.tree.Heap.insert
 *
 * 这里假定 data 中的数据从 index 为 1 的位置开始存储
 *
 */
fun buildHeapBottomToUp(data: IntArray) {
    for (i in 2 until data.size) {
        var cur = i
        while (cur / 2 > 0) {
            // 比父节点大，则交换位置
            if (data[cur / 2] < data[cur]) {
                data.swap(cur / 2, cur)
            } else {
                break
            }
            cur /= 2
        }
    }
}

/**
 * 自上而下地堆化
 *   - 针对这组数组，从后向前地逐个进行堆化处理，叶子节点位置的数据已经处于最底层，无须在向下堆化
 *   - 最后一个叶子节点索引为 n, 其父节点为 n/2, 也就是最后一个非叶子节点
 *   - 因此该完全二叉树中, 从 n/2+1 开始的节点都是叶子节点
 *
 * @see me.exercise.tree.Heap.removeTop
 * @see me.exercise.tree.Heap.heapify
 */
fun buildHeapUpToBottom(data: IntArray, size: Int) {
    for (i in size / 2 downTo 1) {
        heapify(data, size, i)
    }
}

/**
 * 堆化操作, 本质上是从某个节点出发，将其放到正确位置的过程.
 */
private fun heapify(array: IntArray, n: Int, i: Int) {
    var cur = i
    while (true) {
        var maxPos = cur

        // cur 小于其左孩子
        if (cur * 2 <= n && array[cur] < array[cur * 2]) {
            maxPos = cur * 2
        }

        // maxPos 小于右孩子
        if (cur * 2 + 1 <= n && array[maxPos] < array[cur * 2 + 1]) {
            maxPos = cur * 2 + 1
        }

        // cur==maxPos 说明 cur 节点已经符合堆的特性，无须进行堆化操作
        if (maxPos == cur) {
            break
        }
        array.swap(cur, maxPos)
        cur = maxPos
    }
}

// 对于已经堆化的数据，将堆顶数据与最后一个数据交换，然后堆的 size - 1, 执行堆化
// 如此重复, 直到堆中只有一个数据, 此时便完成了排序
// data[] 是已经完成堆化的数据
// 因为存在堆顶元素与最后一个叶子节点交换的操作，因此堆排序并不是稳定的排序算法
fun heapSort(data: IntArray, size: Int) {
    var n = size
    while (n > 1) {
        data.swap(1, n)
        n--
        heapify(data, n, 1)
    }
}

fun main() {
    val array = intArrayOf(-1, 3, 4, 1, 5, 7, 2)
    buildHeapUpToBottom(array, 6)
    heapSort(array, 6)

    // -1 1 2 3 4 5 7
    array.print()
}
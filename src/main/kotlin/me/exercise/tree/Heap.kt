package me.exercise.tree

import me.exercise.sort.swap

/**
 * 堆是一种特殊的树：
 *  1）完全二叉树；
 *  2）对于大顶堆，每个节点的都大于其左右子节点；
 *        小顶堆，每个节点都小于其子节点
 *
 * 堆使用数组来存储
 * [][1][2][3][4][5]
 *           1
 *       2       3
 *    4    5
 * 空出数组的索引为 0 的空间，从 1 开始方便计算
 * 对于节点 i，其左子节点为 i*2，右子节点为 i*2+1，父节点为 i/2
 *
 * 向堆中插入新数据，并且调整数据的位置使其满足堆的特性，这个过程称之为“堆化”(heapify)
 *
 * 接下来的示例均以「大顶堆」为例来实现
 */
class Heap(val capacity: Int = 10) {

    // 使用数组存储堆，不支持扩容
    // capacity 为堆可存元素的最大数量，空出位置 0 ，因此数组 size 初始化为 capacity+1
    private val array = IntArray(capacity + 1)

    // 堆中元素的数量
    private var count = 0

    /**
     * 向堆中插入数据
     * 思路如下：
     *  - 将数据插入末尾
     *  - 与其父节点比较
     *      - 如果大于父节点，那么与父节点调整位置
     *      - 如果小于父节点，那么已经调整到了正确的位置，完成了堆化
     *
     * 这种堆化方式因为是将数据插入最末，右逐步向上调整完成堆化，因此是一种“自下而上的堆化”
     */
    fun insert(data: Int) {
        // 堆已满
        if (count == capacity) {
            return
        }

        ++count
        array[count] = data
        var i = count
        while (i / 2 > 0 && array[i] > array[i / 2]) {
            array.swap(i, i / 2)
            i /= 2
        }
    }

    /**
     * 移除堆顶元素
     *
     * 将末尾元素直接覆盖到堆顶元素的位置，然后进行自上而下的堆化：
     *  - 如果元素小于左子节点，那么与之交换位置
     *  - 如果元素小于右子节点，那么与之交换位置
     *  - 直至满足父子节点的大小关系为止
     */
    fun removeTop() {
        // 堆为空
        if (count == 0) {
            return
        }
        array[1] = array[count]
        --count
        heapify(array, count, 1)
    }

    /**
     * 堆化操作, 本质上是从某个节点出发，将其放到正确位置的过程.
     */
    private fun heapify(array: IntArray, n: Int, i: Int) {
        var cur = i
        while (true) {
            var maxPos = cur

            // cur 小于其左孩子
            if (cur * 2 < n && array[cur] < array[cur * 2]) {
                maxPos = cur * 2
            }

            // maxPos 小于右孩子
            if (cur * 2 + 1 < n && array[maxPos] < array[cur * 2 + 1]) {
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
}

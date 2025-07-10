package me.exercise.tree

import java.util.*

/**
 * 给定一个长度为 n 的无序数组 nums ，请返回数组中最大的 k 个元素。
 */
fun main() {
    val nums = intArrayOf(5, 4, 8, 100, 44, 88)

    // 44 88 100
    topKHeap(nums, 3).forEach { print("$it ") }

}

fun topKHeap(nums: IntArray, k: Int): Queue<Int> {
    // 小顶堆
    val heap = PriorityQueue<Int>()

    // 将数组的前 k 个元素入堆
    for (i in 0 until k) {
        heap.add(nums[i])
    }

    // 从第 k+1 个元素开始，保持堆的长度为 k
    for (j in k until nums.size) {
        // 若当前元素大于堆顶元素，则将堆顶元素出堆、当前元素入堆
        if (nums[j] > heap.peek()) {
            heap.poll()
            heap.add(nums[j])
        }
    }

    return heap
}
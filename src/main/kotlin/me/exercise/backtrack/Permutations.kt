package me.exercise.backtrack

/**
 * 给定一个不含重复数字的数组 nums，返回其所有可能的 全排列。
 *
 * 输入: [1, 2, 3]
 * 输出: [
 *  [1,2,3],
 *  [1,3,2],
 *  [2,1,3],
 *  [2,3,1],
 *  [3,1,2],
 *  [3,2,1]
 * ]
 */

fun main() {
    val array = intArrayOf(1, 2, 3)
    val result = permute(array)
    for (path in result) {
        path.forEachIndexed { index, i ->
            when (index) {
                0 -> {
                    print("[$i, ")
                }

                path.size - 1 -> {
                    println("$i]")
                }

                else -> {
                    print("$i, ")
                }
            }
        }
    }
}

fun permute(nums: IntArray): List<List<Int>> {
    // 记录结果
    val result = mutableListOf<List<Int>>()
    // 标记数字是否使用过, used[i] = true 表示 nums[i] 这个数字被使用过
    val used = BooleanArray(nums.size)

    fun backtrack(path: MutableList<Int>) {
        // 完成一组排列
        if (path.size == nums.size) {
            result.add(path.toList())
            return
        }

        for (i in 0 until nums.size) {
            if (used[i].not()) {
                used[i] = true
                path.add(nums[i])
                backtrack(path)
                // 回溯的过程中，将使用的元素“复位”
                path.removeLast()
                used[i] = false
            }
        }
    }

    backtrack(mutableListOf())

    return result
}
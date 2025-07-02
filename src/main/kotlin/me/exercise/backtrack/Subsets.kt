package me.exercise.backtrack

/**
 * 给定一个不含重复元素的整数数组 nums，返回该数组所有可能的 子集（幂集）。
 *
 * 例如
 * 输入: [1,2,3]
 * 输出: [
 *  [], [1], [2], [3],
 *  [1,2], [1,3], [2,3],
 *  [1,2,3]
 * ]
 */

fun main() {
    val array = intArrayOf(1, 2, 3)
    val result = subset(array)
    for (path in result) {
        val strPath = StringBuilder("[")
        path.forEachIndexed { index, i ->
            strPath.append(
                if (index == path.size - 1) i
                else "$i, "
            )
        }
        strPath.append("]")
        println(strPath)
    }
}

fun subset(nums: IntArray): List<List<Int>> {
    val result = mutableListOf<List<Int>>()

    fun backtrack(start: Int, path: MutableList<Int>) {
        result.add(path.toList())
        for (i in start until nums.size) {
            path.add(nums[i])
            backtrack(i + 1, path)
            path.removeLast()
        }
    }

    backtrack(0, mutableListOf())
    return result
}
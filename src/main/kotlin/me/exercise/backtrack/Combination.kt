package me.exercise.backtrack

fun main() {
    val candidates = intArrayOf(2, 3, 5)
    val target = 8

    val result = combinationSum(candidates, target)

    result.forEach { combine ->
        val sb = StringBuilder("[ ")
        combine.forEach {
            sb.append("$it ")
        }
        sb.append("]")
        println(sb)
    }

}

/**
 * https://leetcode.cn/problems/combination-sum/description/
 *
 * 给定一个不含重复元素的整数数组 candidates，从其中找出所有和为 target 的组合
 * candidates 中的同一个数字可以无限制重复被选取。如果至少一个数字的被选数量不同，则两种组合是不同的。
 */
fun combinationSum(candidates: IntArray, target: Int): List<List<Int>> {

    val result = mutableListOf<List<Int>>()

    // target: 当次剩余的 target
    // index: 当次用到了 candidates 的第 index 个数字
    // combine: 当次已经完成的组合
    fun backtrack(nums: IntArray, target: Int, index: Int, combination: MutableList<Int>) {
        if (target == 0) {
            result.add(combination.toList())
        } else {
            for (i in index until nums.size) {
                if (target < nums[i]) continue
                combination.add(nums[i])
                // 选用了第 index 个，下次仍然可以重复使用第 index
                backtrack(nums, target - nums[i], i, combination)
                // 回溯
                combination.removeLast()
            }
        }
    }

    backtrack(candidates, target, 0, mutableListOf())

    return result
}


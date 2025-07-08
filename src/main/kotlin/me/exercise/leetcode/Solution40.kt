package me.exercise.leetcode

fun main() {
    val list = intArrayOf(2, 5, 2, 1, 2)
    val target = 5
    val result = combinationSum2(list, target)

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
 * https://leetcode.cn/problems/combination-sum-ii/description/
 *
 * 给定一个候选人编号的集合 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 *
 * candidates 中的每个数字在每个组合中只能使用 一次 。
 *
 * 注意：解集不能包含重复的组合。
 */

fun combinationSum2(candidates: IntArray, target: Int): List<List<Int>> {
    // 排序，以方便后续剪枝操作
    candidates.sort()

    val result = mutableListOf<List<Int>>()

    fun backtrack(nums: IntArray, target: Int, index: Int, combination: MutableList<Int>) {
        if (target == 0) {
            result.add(combination.toList())
        }
        for (i in index until nums.size) {
            if (candidates[i] > target) continue

            // 在递归的同一层级里，当前使用的元素与前面一个使用的元素相同，那么直接跳过
            // 不再进行接下来的操作，以达到去除重复组合的目的
            if (i > index && candidates[i] == candidates[i - 1]) continue
            combination.add(candidates[i])
            backtrack(nums, target - candidates[i], i + 1, combination)
            combination.removeLast()
        }
    }
    backtrack(candidates, target, 0, mutableListOf())
    return result
}
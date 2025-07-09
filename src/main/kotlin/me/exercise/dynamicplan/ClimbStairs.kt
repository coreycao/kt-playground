package me.exercise.dynamicplan

/**
 * 共 n 阶楼梯，每次走一步或两步，爬到顶部共多少种走法
 *
 * 递推公式
 * f(n) = f(n-1) + f(n-2)
 * ...
 * f(2) = f(1) + f(0), f(1) = 1, f(0) = 1
 *
 * 递归法
 * @see me.exercise.recursion.climbRecursion
 */
fun climb(n: Int): Int {
    if (n == 0 || n == 1) {
        return 1
    }

    val result = IntArray(n + 1)
    result[0] = 1
    result[1] = 1
    for (i in 2..n) {
        result[i] = result[i - 1] + result[i - 2]
    }
    return result[n]
}

/**
 * 746. 使用最小花费爬楼梯
 * 给你一个整数数组 cost ，其中 cost[i] 是从楼梯第 i 个台阶向上爬需要支付的费用。
 * 一旦你支付此费用，即可选择向上爬一个或者两个台阶。
 * 你可以选择从下标为 0 或下标为 1 的台阶开始爬楼梯。
 * 请你计算并返回达到楼梯顶部的最低花费。
 * https://leetcode.cn/problems/min-cost-climbing-stairs
 *
 * 思路：
 * 数组 dp[i] 记录到达第 i 个台阶 所需的最小花费
 * 假设有 n 个台阶，那么 dp 的容量为 n+1，在 n+1 位置记录的是离开第 n 个台阶的花费
 *
 * 状态转移方程：dp[i] = min(dp[i-1] + cost[i-1], dp[i-2] + cost[i-2])
 *
 * 初始状态
 * dp[0] = 0  // 表示在楼梯下方，还没开始
 * dp[1] = 0  // 表示从第0或第1个台阶开始，都可以，起点不花费
 */
fun minCostClimbingStairs(cost: IntArray): Int {
    val dp = IntArray(cost.size + 1)
    dp[0] = 0
    dp[1] = 0
    for (i in 2 until dp.size) {
        dp[i] = minOf(
            dp[i - 1] + cost[i - 1],
            dp[i - 2] + cost[i - 2]
        )
    }
    return dp.last()
}

fun main() {
    val cost = intArrayOf(10, 15, 20)
    minCostClimbingStairs(cost).also { println(it) }
}
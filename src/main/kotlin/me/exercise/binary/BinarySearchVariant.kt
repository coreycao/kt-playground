package me.exercise.binary

/**
 * 二分查找的几个变体
 *
 * 若给定的序列中存在重复相等的元素时
 * 1）查找第一个值等于给定值的元素
 * 2）查找最后一个值等于给定值的元素
 * 3）查找第一个大于等于给定值的元素
 * 4）查找最后一个小于等于给定值的元素
 */

/**
 * 1）查找第一个值等于给定值的元素
 */
fun binarySearchFirst(nums: IntArray, target: Int): Int {
    var low = 0
    var high = nums.size - 1
    while (low <= high) {
        val mid = low + (high - low) / 2
        if (target > nums[mid]) {
            low = mid + 1
        } else if (target < nums[mid]) {
            high = mid - 1
        } else {
            if (mid == 0 || nums[mid - 1] < target) return mid
            else high = mid - 1
        }
    }
    return -1
}

/**
 * 2）查找最后一个值等于给定值的元素
 */
fun binarySearchLast(nums: IntArray, target: Int): Int {
    var low = 0
    var high = nums.size - 1
    while (low <= high) {
        val mid = low + (high - low) / 2
        if (target > nums[mid]) {
            low = mid + 1
        } else if (target < nums[mid]) {
            high = mid - 1
        } else {
            if (mid == nums.size - 1 || nums[mid + 1] != target) return mid
            else high = mid - 1
        }
    }
    return -1
}

/**
 * 3）查找第一个大于等于给定值的元素
 */
fun binarySearchFirstLarger(nums: IntArray, target: Int): Int {
    var low = 0
    var high = nums.size - 1
    while (low <= high) {
        val mid = low + (high - low) / 2
        if (nums[mid] >= target) {
            if (mid == 0 || nums[mid - 1] < target) return mid
            else high = mid - 1
        } else {
            low = mid + 1
        }
    }
    return -1
}

/**
 * 4）查找最后一个小于等于给定值的元素
 */
fun binarySearchLastLesser(nums: IntArray, target: Int): Int {
    var low = 0
    var high = nums.size - 1
    while (low <= high) {
        val mid = low + (high - low) / 2
        if (nums[mid] <= target) {
            if (mid == nums.size - 1 || nums[mid + 1] > target) return mid
            else low = mid + 1
        } else {
            high = mid - 1
        }
    }
    return -1
}



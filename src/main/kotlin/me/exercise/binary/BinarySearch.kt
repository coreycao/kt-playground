package me.exercise.binary

fun <T : Comparable<T>> binarySearchRecursion(nums: Array<T>, low: Int, high: Int, target: T): Int {

    if (high < low) {
        return -1
    }
    // 防溢出
    var mid = low + (high - low) / 2
    return if (nums[mid] == target) {
        mid
    } else if (nums[mid] < target) {
        binarySearchRecursion(nums, mid + 1, high, target)
    } else {
        binarySearchRecursion(nums, low, mid - 1, target)
    }
}

fun <T : Comparable<T>> binarySearch(nums: Array<T>, low: Int = 0, high: Int = nums.size - 1, target: T): Int {
    var left = low
    var right = high
    while (left <= right) {
        // 防溢出
        var mid = left + (right - left) / 2
        when {
            nums[mid] == target -> return mid
            nums[mid] > target -> right = mid - 1
            nums[mid] < target -> left = mid + 1
        }
    }
    return -1
}

fun main() {
    val nums = arrayOf(16, 26, 36, 46, 76, 86, 93, 106)

    // 4
    println(binarySearchRecursion(nums, 0, nums.size - 1, 76))

    // 6
    println(binarySearch(nums = nums, target = 93))
}


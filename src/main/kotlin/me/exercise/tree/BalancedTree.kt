package me.exercise.tree

import kotlin.math.abs

/**
 * 空树是平衡树
 *
 * 左子树和右子树的高度差不超过 1 且 左子树和右子树也是平衡的，那么该树为平衡的
 */
fun <T> isTreeBalanced(root: TreeNode<T>?): Boolean {
    if (root == null) {
        return true
    }

    val depthDiff = abs(depthOfTree(root.left) - depthOfTree(root.right))
    return depthDiff <= 1 && isTreeBalanced(root.left) && isTreeBalanced(root.right)
}
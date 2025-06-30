package me.exercise.tree

import java.util.*
import kotlin.math.max

fun main() {
    val tree = TreeNode.tree
    // 4
    println("depth: ${depthOfTree(tree)}")

    val tree2 = TreeNode.unBalancedTree
    // 5
    println("depth: ${depthOfTreeByLayer(tree2)}")
}

fun <T> depthOfTree(root: TreeNode<T>?): Int {
    if (root == null) {
        return 0
    }

    return max(depthOfTree(root.left), depthOfTree(root.right)) + 1
}

/**
 * 通过层序遍历计算树的高度
 */
fun <T> depthOfTreeByLayer(root: TreeNode<T>?): Int {
    if (root == null) {
        return 0
    }

    val queue: Queue<TreeNode<T>> = LinkedList()
    queue.add(root)

    var countOfCurrentLayer = 1     // 当前遍历层级的节点数目
    var countOfNextLayer = 0        // 下一层级的节点数目
    var layerCount = 0              // 统计层数

    while (queue.isNotEmpty()) {
        // 节点出队
        val current = queue.poll()
        // 访问节点
        print("${current.value} ")
        countOfCurrentLayer--

        if (current.left != null) {
            queue.add(current.left)
            countOfNextLayer++
        }

        if (current.right != null) {
            queue.add(current.right)
            countOfNextLayer++
        }

        // 遍历完了一整层
        if (countOfCurrentLayer == 0) {
            // 层数+1
            layerCount++
            // 打印换行
            println()
            // 更新 countOfCurrentLayer
            countOfCurrentLayer = countOfNextLayer
            // 充值 countOfNextLayer
            countOfNextLayer = 0
        }
    }
    return layerCount
}
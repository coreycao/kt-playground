package me.exercise.tree

import java.util.*

fun main() {
    val tree = TreeNode.tree
    hierarchyTraversal(tree)
}

/**
 * 使用一个队列来辅助实现层序遍历
 */
fun <T> hierarchyTraversal(root: TreeNode<T>?) {
    if (root == null) {
        return
    }
    val queue: Queue<TreeNode<T>> = LinkedList()
    queue.add(root)
    while (queue.isNotEmpty()) {
        val cur = queue.poll()
        println(cur.value)

        if (cur.left != null) {
            queue.add(cur.left)
        }

        if (cur.right != null) {
            queue.add(cur.right)
        }
    }
}
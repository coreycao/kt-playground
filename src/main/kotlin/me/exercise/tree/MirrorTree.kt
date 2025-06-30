package me.exercise.tree

fun main() {
    val tree = TreeNode.tree
    printTreeVertical(tree)

    println("after mirror: ")

    printTreeVertical(mirror(tree))
}

/**
 * 镜像反转二叉树
 */
fun <T> mirror(root: TreeNode<T>?): TreeNode<T>? {
    if (root != null) {
        val tmp = root.left
        root.left = mirror(root.right)
        root.right = mirror(tmp)
    }
    return root
}
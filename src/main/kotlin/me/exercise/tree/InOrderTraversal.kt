package me.exercise.tree

fun <T> inOrderTraversal(root: TreeNode<T>?) {
    if (root == null) {
        return
    }

    inOrderTraversal(root.left)
    println(root.value)
    inOrderTraversal(root.right)
}
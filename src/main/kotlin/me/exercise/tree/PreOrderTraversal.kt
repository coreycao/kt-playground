package me.exercise.tree

fun <T> preOrderTraversal(root: TreeNode<T>?) {
    if (root == null){
        return
    }
    println(root.value)
    preOrderTraversal(root.left)
    preOrderTraversal(root.right)
}
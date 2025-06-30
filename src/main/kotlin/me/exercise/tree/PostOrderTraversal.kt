package me.exercise.tree

fun <T> postOrder(root: TreeNode<T>?){
    if (root == null){
        return
    }

    postOrder(root.left)
    postOrder(root.right)
    println(root.value)
}
package me.exercise.tree

fun main() {

    /**
     * -            1
     * -        2       3
     * -      4   5       6
     * -        7   8
     */
    val tree = TreeNode.tree

    // 1 2 3 5 7 8 3 6
    // preOrderRecursive(tree)

    preOrderByStack(tree)
}

fun <T> preOrderRecursive(root: TreeNode<T>?, visit: (TreeNode<T>) -> Unit = { node -> print("${node.value} ") }) {
    if (root == null) {
        return
    }
    // println(root.value)
    visit(root)
    preOrderRecursive(root.left)
    preOrderRecursive(root.right)
}

/**
 * 非递归方式前序遍历二叉树
 *
 * 使用一个 stack 来实现，本质上是把递归调用的过程在应用代码的层面使用栈来实现了一下
 */
fun <T> preOrderByStack(root: TreeNode<T>?, visit: (TreeNode<T>) -> Unit = { node -> print("${node.value} ") }) {
    if (root == null) return
    val helperStack = ArrayDeque<TreeNode<T>>()
    helperStack.addFirst(root)
    while (helperStack.isNotEmpty()) {
        val cur = helperStack.removeFirst()
        visit(cur)

        if (cur.right != null) {
            helperStack.addFirst(cur.right!!)
        }

        if (cur.left != null) {
            helperStack.addFirst(cur.left!!)
        }
    }
}
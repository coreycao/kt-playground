package me.exercise.tree

fun main() {
    /**
     * -            1
     * -        2       3
     * -      4   5       6
     * -        7   8
     */
    val tree = TreeNode.tree

    // 4 2 7 5 8 1 3 6
    inOrderRecursive(tree)

    inOrderByStack(tree)
}

fun <T> inOrderRecursive(root: TreeNode<T>?, visit: (TreeNode<T>) -> Unit = { node -> print("${node.value} ") }) {
    if (root == null) {
        return
    }

    inOrderRecursive(root.left)
    visit(root)
    inOrderRecursive(root.right)
}

fun <T> inOrderByStack(root: TreeNode<T>?, visit: (TreeNode<T>) -> Unit = { node -> print("${node.value} ") }) {
    if (root == null) return
    var current = root
    val helperStack = ArrayDeque<TreeNode<T>>()
    while (helperStack.isNotEmpty() || current != null) {

        // 一路向左 入栈
        while (current != null) {
            helperStack.addFirst(current)
            current = current.left
        }

        // 此时的栈顶元素其左孩子为空
        //  或者其左子树已完成遍历出栈
        //  既然左子树已完成遍历，那么按照 inOrder 的规则，遍轮到了该元素，遍历完当前元素后，继续操作右子树
        if (helperStack.isNotEmpty()) {
            current = helperStack.removeFirst()
            visit(current)
            current = current.right
        }
    }
}
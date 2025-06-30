package me.exercise.tree

fun <T> printTreeVertical(root: TreeNode<T>?) {
    if (root == null) return

    val height = depthOfTree(root)
    val width = (1 shl height) - 1  // 2^height - 1
    val matrix = MutableList(height) { MutableList(width) { " " } }

    fillMatrix(root, matrix, 0, 0, width - 1)

    for (row in matrix) {
        println(row.joinToString(""))
    }
}

fun <T> fillMatrix(
    node: TreeNode<T>?,
    matrix: MutableList<MutableList<String>>,
    row: Int,
    left: Int,
    right: Int
) {
    if (node == null || row >= matrix.size) return

    val mid = (left + right) / 2
    matrix[row][mid] = node.value.toString()

    fillMatrix(node.left, matrix, row + 1, left, mid - 1)
    fillMatrix(node.right, matrix, row + 1, mid + 1, right)
}
package me.exercise.tree

/**
 * Binary Search Tree，二叉查找树
 * 对于树中的任一节点，左子树的节点都小于该节点，右子树的节点都大于该节点
 *
 * BST 支持快速的查找、插入、删除操作
 * 中序遍历可以按顺序访问 BST
 *
 * TODO:
 * - 删除操作
 *
 */

fun main() {
    /**
     * -            7
     * -        4       8
     * -      3   5       10
     * -     2     6     9
     * -    1
     */
    val BST = TreeNode.BST

    // 中序遍历可以按顺序访问 BST
    inOrderRecursive(BST)

    val target = 6
    // 6
    val targetNode = find(BST, target)
    println("$targetNode with value ${targetNode?.value}")

    // 插入 11
    val BST_11 = insert(BST, 11)
    inOrderRecursive(BST_11)

}

fun <T : Comparable<T>> find(root: TreeNode<T>?, target: T): TreeNode<T>? {
    var current = root
    while (current != null) {
        if (current.value!! > target) {
            current = current.left
        } else if (current.value!! < target) {
            current = current.right
        } else {
            return current
        }
    }
    return null
}

/**
 * 如果要插入的数据比节点的数据大，并且节点的右子树为空，就将新数据直接插到右子节点的位置；
 *      如果不为空，就再递归遍历右子树，查找插入位置。
 *
 * 同理，如果要插入的数据比节点数值小，并且节点的左子树为空，就将新数据插入到左子节点的位置；
 *      如果不为空，就再递归遍历左子树，查找插入位置。
 *
 * 对于相等值的处理有如下两种方案：
 *  1）拉链法；
 *  2）向右插入法：
 *      找到相等节点后，将新数据放到这个节点的右子树，也就是说，把这个新插入的数据当作大于这个节点的值来处理
 *      当要查找数据的时候，遇到值相同的节点，我们并不停止查找操作，而是继续在右子树中查找，直到遇到叶子节点，才停止。这样就可以把键值等于要查找值的所有节点都找出来。
 */
fun <T : Comparable<T>> insert(root: TreeNode<T>?, data: T): TreeNode<T> {
    if (root == null) {
        return TreeNode(data)
    }

    var current = root
    while (current != null) {
        if (current.value!! > data) {
            if (current.left == null) {
                current.left = TreeNode(data)
                break
            } else {
                current = current.left
            }
        } else if (current.value!! < data) {
            if (current.right == null) {
                current.right = TreeNode(data)
                break
            } else {
                current = current.right
            }
        } else {
            // 已经有相等的值，暂时不再重复插入
            break
        }
    }
    return root
}
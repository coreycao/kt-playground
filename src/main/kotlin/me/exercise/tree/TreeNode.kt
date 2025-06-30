package me.exercise.tree

/**
 * 树的节点
 *
 * 二叉树是一种树形结构，其特点是每个结点至多只有两颗子树，并且二叉树的子树有左右之分
 * 非空二叉树叶子结点数等于度为 2 的结点的个数加 1，即 N0 = N2 + 1
 * 非空二叉树上第K层上至多有 2^(k-1) 个结点
 * 高度为 H 的二叉树至多有 2^H - 1 个结点
 */
class TreeNode<T>(
    var value: T?,
    var left: TreeNode<T>? = null,
    var right: TreeNode<T>? = null
) {

    // var parent: TreeNode<T?>? = null

    companion object {

        /**
         * -            1
         * -        2       3
         * -      4   5       6
         * -        7   8
         */
        val tree: TreeNode<Int>
            get() {
                val root = TreeNode(1)

                root.left = TreeNode(2)
                root.right = TreeNode(3)

                root.left!!.left = TreeNode(4)
                root.left!!.right = TreeNode(5)

                root.right!!.right = TreeNode(6)

                root.left!!.right!!.left = TreeNode(7)
                root.left!!.right!!.right = TreeNode(8)

                return root
            }

        /**
         * -            1
         * -        2       3
         * -      4   5       6
         * -     7     8     9
         * -   10
         */
        val unBalancedTree: TreeNode<Int>
            get() {
                val root = TreeNode(1)
                root.left = TreeNode(2)
                root.right = TreeNode(3)
                root.left!!.left = TreeNode(4)
                root.left!!.right = TreeNode(5)
                root.right!!.right = TreeNode(6)
                root.left!!.left!!.left = TreeNode(7)
                root.left!!.right!!.right = TreeNode(8)
                root.right!!.right!!.left = TreeNode(9)
                root.left!!.left!!.left!!.left = TreeNode(10)
                return root
            }

        /**
         * 二叉搜索树
         *
         *
         * 左节点比根 小
         * 右节点比根 大
         *
         *
         * -            7
         * -        4       8
         * -      3   5       10
         * -     2     6     9
         * -   1
         */
        val bST: TreeNode<Int>
            get() {
                val root = TreeNode(7)
                root.left = TreeNode(4)
                root.right = TreeNode(8)
                root.left!!.left = TreeNode(3)
                root.left!!.right = TreeNode(5)
                root.right!!.right = TreeNode(10)
                root.left!!.left!!.left = TreeNode(2)
                root.left!!.right!!.right = TreeNode(6)
                root.right!!.right!!.left = TreeNode(9)
                root.left!!.left!!.left!!.left = TreeNode(1)
                return root
            }

        /**
         * 完全二叉树
         *
         *
         * 完全二叉树：叶节点只能出现在最下层和次下层，并且最下面一层的结点都集中在该层最左边的若干位置的二叉树
         *
         *
         * -            7
         * -        4       8
         * -      3   5   10
         *
         */
        val cBT: TreeNode<Int>
            get() {
                val root = TreeNode(7)
                root.left = TreeNode(4)
                root.right = TreeNode(8)
                root.left!!.left = TreeNode(3)
                root.left!!.right = TreeNode(5)
                root.right!!.left = TreeNode(10)
                return root
            }

        /**
         * 对称树
         *
         * -            7
         * -        4       4
         * -      3   5   5   3
         */
        val symmetricTree: TreeNode<Int>
            get() {
                val root = TreeNode(7)
                root.left = TreeNode(4)
                root.left!!.left = TreeNode(3)
                root.left!!.right = TreeNode(5)

                root.right = TreeNode(4)
                root.right!!.left = TreeNode(5)
                root.right!!.right = TreeNode(3)
                return root
            }
    }
}
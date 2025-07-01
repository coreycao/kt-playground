package me.exercise.graph

import java.util.*

/**
 * 邻接表形式的表示图
 *
 * 邻接表解决了邻接矩阵浪费空间的问题，但是访问效率没有邻接矩阵高
 * 在实际应用中，采用与 HashMap 类似的优化思路，如使用红黑树等数据结构来改善访问效率
 *
 * 在实际应用中，可以通过同时维护一个“逆邻接表”，以能够快速获取某个顶点“被指向”的数据
 */
class AdjacencyListGraph(val n: Int) {
    val adjList = Array(n) {
        LinkedList<Int>()
    }

    fun addEdge(from: Int, to: Int) {
        adjList[from].add(to)
        adjList[to].add(from)
    }

    /**
     * 0 -- 1 -- 2
     * |    |    |
     * 3 -- 4 -- 5
     *      |    |
     *      6 -- 7
     */
    companion object {
        fun sampleAdjListGraph(): AdjacencyListGraph {
            return AdjacencyListGraph(8).apply {
                addEdge(0, 1)
                addEdge(0, 3)
                addEdge(1, 2)
                addEdge(1, 4)
                addEdge(2, 5)
                addEdge(3, 4)
                addEdge(4, 5)
                addEdge(4, 6)
                addEdge(5, 7)
                addEdge(6, 7)
            }
        }
    }
}
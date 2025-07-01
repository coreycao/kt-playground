package me.exercise.graph

fun main() {
    val graph = AdjacencyListGraph.sampleAdjListGraph()
    depthFirstVisit(graph)
}

/**
 * 深度优先遍历
 *
 * 回溯思想的典型
 *
 * 使用递归实现
 *
 * 若要执行搜索，同样的使用一个 route 数据记录搜索路径即可
 */
fun depthFirstVisit(graph: AdjacencyListGraph) {
    val visited = BooleanArray(graph.n)
    for (i in 0 until graph.n) {
        if (visited[i].not()) {
            dfsRecursive(graph, visited, i)
        }
    }
}

private fun dfsRecursive(graph: AdjacencyListGraph, visited: BooleanArray, vertex: Int) {
    println(vertex)
    visited[vertex] = true
    // for (i in 0 until graph.adjList[vertex].size)
    for (v in graph.adjList[vertex]) {
        if (visited[v].not()) {
            dfsRecursive(graph, visited, v)
        }
    }
}
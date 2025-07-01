package me.exercise.graph

import java.util.*

fun main() {
    val graph = AdjacencyListGraph.sampleAdjListGraph()

    val route = breadthFirstSearch(graph, target = 7)
    printSearchRoute(route, 0, 7)

    breadthFirstVisit(graph)
}

/**
 * 广度优先搜索，Breadth First Visit
 *
 * 形象地来说就是“地毯式”层层推进地遍历
 *
 * 实现方式上借助一个队列来实现
 *
 * 得到的路径是一条最短路径
 */
fun breadthFirstSearch(graph: AdjacencyListGraph, from: Int = 0, target: Int): IntArray {
    // 图中没有顶点
    if (graph.n == 0) {
        println("Empty Graph")
        return intArrayOf()
    }

    if (from == target) {
        println("from == target")
        return intArrayOf()
    }

    // 记录搜索路径 route[B] = A 表示 A 搜索至 B
    val route = IntArray(graph.n) {
        -1
    }

    // 用来记录顶点是否已经被访问过
    val visited = BooleanArray(graph.n)

    val queue: Queue<Int> = LinkedList()
    queue.add(from)
    visited[from] = true
    while (queue.isNotEmpty()) {
        val cur = queue.poll()

        // 遍历 cur 的相邻顶点
        for (i in 0 until graph.adjList[cur].size) {
            // 如果 cur 相邻顶点没有被访问过, 访问它, 并更改 visited 状态
            if (visited[graph.adjList[cur][i]].not()) {
                // 记录路径
                route[graph.adjList[cur][i]] = cur

                // 搜索到了目标
                if (graph.adjList[cur][i] == target) {
                    break
                }

                queue.add(graph.adjList[cur][i])
                visited[graph.adjList[cur][i]] = true
            }
        }
    }
    return route
}

/**
 * 打印 from -> to 的搜索路径
 *
 * route 为 BFS 得到的记录结果
 * route[B] = A 的含义是搜索过程中从 A 走向了 B
 * 因此这里使用递归的方式“逆序”地打印出正确的路径
 */
fun printSearchRoute(route: IntArray, from: Int, target: Int){
    if (route[target] != -1 && from != target){
        printSearchRoute(route, from, route[target])
    }
    print("$target ")
}

/**
 * 广度优先遍历
 */
fun breadthFirstVisit(graph: AdjacencyListGraph) {
    // 图中没有顶点
    if (graph.n == 0) {
       return
    }

    // 用来记录顶点是否已经被访问过
    val visited = BooleanArray(graph.n)

    val queue: Queue<Int> = LinkedList()
    queue.add(0)
    visited[0] = true
    println(0)
    while (queue.isNotEmpty()) {
        val cur = queue.poll()

        // 遍历 cur 的相邻顶点
        // for (i in 0 until graph.adjList[cur].size) {
        // vertex = graph.adjList[cur][i]
        for (vertex in graph.adjList[cur]) {
            // 如果 cur 相邻顶点没有被访问过, 访问它, 并更改 visited 状态
            if (visited[vertex].not()) {
                queue.add(vertex)
                println(vertex)
                visited[vertex] = true
            }
        }
    }
}

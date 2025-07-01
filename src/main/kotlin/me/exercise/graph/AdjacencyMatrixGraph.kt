package me.exercise.graph

/**
 * 使用邻接矩阵表示图
 *
 * - 在有向图中，
 *      - 顶点 i 指向 j，那么matrix[i][j] == 1
 *      - 顶点 j 指向 i，那么matrix[j][i] == 1
 *      - 若要表示权重，那么在矩阵中直接存入 weight 值即可
 * - 在无向图中
 *      - 顶点 i 和 j 之间存在连接关系，那么：matrix[i][j] == 1 && matrix[j][i] == 1
 *      - 或者只存 matrix[i][j] == 1 即可
 *
 * 矩阵在数据结构上使用数组来表示，得益于数组快速访问的特性
 *   - 优点是快速访问，能够快速获得顶点之间的连接关系；
 *   - 缺点是浪费存储空间，尤其是无向图和稀疏图的情况下。
 */
class AdjacencyMatrixGraph<V>(val n: Int) {

    private val vertexes = mutableListOf<V>()

    private val adjMatrix = Array(n) {
        IntArray(n) { 0 }
    }

    private var edges = 0

    fun addVertex(vertex: V) = vertexes.add(vertex)

    fun addEdge(i: Int, j: Int, weight: Int = 1) {
        adjMatrix[i][j] = weight
        adjMatrix[j][i] = weight
        this.edges++
    }

    fun numOfVertexes() = vertexes.size

    fun numOfEdges() = edges

    companion object {
        /**

        V0 —— V1 —— V2
        ｜     ｜
        V3 —— V4
        ｜  /
        V5

        BFS: V0 V1 V3 V2 V4 V5
        DFS: V0 V1 V2 V4 V3 V5
         */
        fun sampleAdjMatrixGraph(): AdjacencyMatrixGraph<String> {
            return AdjacencyMatrixGraph<String>(6).apply {
                addVertex("V0")
                addVertex("V1")
                addVertex("V2")
                addVertex("V3")
                addVertex("V4")
                addVertex("V5")
                addEdge(0, 1)
                addEdge(0, 3)
                addEdge(1, 2)
                addEdge(1, 4)
                addEdge(3, 4)
                addEdge(3, 5)
                addEdge(4, 5)
            }
        }
    }
}
package me.exercise.stack

interface CustomQueue<T> {
    fun enqueue(item: T): Boolean
    fun dequeue(): T?
    fun isEmpty(): Boolean
}

/**
 * 使用数组实现的队列，队列满时可以自动扩容并迁移数据
 * 出队时数组前面会空出位置，入队时还需考虑数组前面有空间的情况
 */
class ArrayQueue<T>(private val initCapacity: Int = 8) : CustomQueue<T> {

    private var queue: Array<T?> = arrayOfNulls(initCapacity)

    private var head = 0

    private var tail = 0

    // 入队
    override fun enqueue(item: T): Boolean {
        if (tail == queue.size) {
            // 此时队列被填满，没有空位，触发 resize 操作
            if (head == 0) {
                // resize
                val resizedQueue: Array<T?> = arrayOfNulls(queue.size * 2)
                queue.copyInto(resizedQueue, startIndex = 0, endIndex = tail - head)
                queue = resizedQueue
                tail -= head
            } else {
                // 此时队尾部触底，但是队首存在空位，触发数据搬移操作
                for (i in head until tail) {
                    queue[i - head] = queue[i]
                }
                tail -= head
                head = 0
            }
        }
        queue[tail] = item
        tail++
        return true
    }

    // 出队
    override fun dequeue(): T? {
        // 队列为空返回 null
        if (tail == head) return null
        else {
            val ret = queue[head]
            head++
            return ret
        }
    }

    override fun isEmpty(): Boolean {
        return tail == head
    }
}

/**
 * 使用数组实现的循环队列
 * 循环队列无法扩容
 */
class CircleQueue<T>(private val capacity: Int) : CustomQueue<T> {
    private var queue: Array<T?> = arrayOfNulls(capacity)

    private var head = 0

    private var tail = 0

    /**
     * 关键是 tail 的计算
     */
    override fun enqueue(item: T): Boolean {
        // 队满
        if ((tail + 1) % capacity == head) {
            return false
        } else {
            queue[tail] = item
            tail = (tail + 1) % capacity
            return true
        }
    }

    override fun dequeue(): T? {
        if (head == tail) {
            return null
        } else {
            val ret = queue[head]
            head = (head + 1) % capacity
            return ret
        }
    }

    override fun isEmpty(): Boolean {
        return head == tail
    }
}

/**
 * 链式队列
 * 除了头结点外，为了方便操作尾部元素，还需随时维护一个尾指针
 */
class LinkedQueue<T> : CustomQueue<T> {
    private var head: Node<T>? = null
    private var tail: Node<T>? = null

    override fun enqueue(item: T): Boolean {
        val node = Node<T>(item, null)
        if (head == null) {
            head = node
            tail = node
        } else {
            tail?.next = node
            tail = tail?.next
        }
        return true
    }

    override fun dequeue(): T? {
        if (head != null) {
            val curHead = head
            head = head?.next
            return curHead?.value
        }
        return null
    }

    override fun isEmpty() = head == null
}

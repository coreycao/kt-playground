package me.exercise.design

import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy

fun main() {
    // proxy pattern
    ProxySearcher(RealSearcher()).search("Design Pattern")

    // dynamic proxy by using InvocationHandler
    dynamicProxy()
}

interface Searcher {
    fun search(keyword: String)
}

class RealSearcher : Searcher {
    override fun search(keyword: String) {
        println("RealSearcher#search $keyword")
    }
}

class ProxySearcher(private val realSearch: Searcher) : Searcher {

    fun validate() {
        println("ProxySearcher#validate")
    }

    fun logging() {
        println("ProxySearcher#logging")
    }

    override fun search(keyword: String) {
        validate()
        realSearch.search(keyword)
        logging()
    }
}

/**
 * 使用 InvocationHandler 实现动态代理
 */

interface Service {
    fun doWork()
}

class RealService : Service {
    override fun doWork() {
        println("RealService#doWork")
    }
}

class ProxyHandler(private val target: Any) : InvocationHandler {
    override fun invoke(
        proxy: Any,
        method: Method,
        args: Array<out Any?>?
    ): Any? {
        println("Before calling ${method.name}")
        val result = method.invoke(target, *(args ?: emptyArray()))
        println("After calling ${method.name}")
        return result
    }
}

fun dynamicProxy() {

    val realService = RealService()

    val proxyInstance = Proxy.newProxyInstance(
        Service::class.java.classLoader,
        arrayOf(Service::class.java),
        ProxyHandler(realService)
    ) as Service

    proxyInstance.doWork()
}
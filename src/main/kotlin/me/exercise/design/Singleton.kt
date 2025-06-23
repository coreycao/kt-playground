package me.exercise.design

fun main() {
    SingletonObject.readSettings()
    SingletonByLazy.instance.readSettings()
    SingletonCompanion.instance().readSetting()
}

/**
 * Kotlin中声明单例最简单有效的方法，能够保证懒加载和线程安全
 */
object SingletonObject {
    private const val SETTING = "ObjectSetting"

    fun readSettings() = SETTING
}

/**
 * 使用 lazy 委托实现懒加载
 * 使用 LazyThreadSafetyMode 保证线程安全
 *  LazyThreadSafetyMode.NONE，非线程安全，适用于单线程环境
 *  LazyThreadSafetyMode.PUBLICATION，在首次访问时并发初始化，但只有一个会成功
 */
class SingletonByLazy private constructor() {
    private val setting = "LazySetting"

    fun readSettings() = setting

    companion object {
        val instance: SingletonByLazy by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            SingletonByLazy()
        }
    }
}

/**
 * 利用 companion 对象初始化实例，接近 Java 中双重检查单例的实现方式
 */
class SingletonCompanion {

    private val setting = "CompanionSetting"

    fun readSetting() = setting

    companion object {

        @Volatile
        private var instance: SingletonCompanion? = null

        fun instance(): SingletonCompanion {
            return instance ?: synchronized(this) {
                instance ?: SingletonCompanion().also { singletonCompanion ->
                    instance = singletonCompanion
                }
            }
        }
    }
}
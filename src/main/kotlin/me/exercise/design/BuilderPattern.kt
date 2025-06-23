package me.exercise.design

/**
 * 何时使用 Builder 模式：
 *  当构建一个对象需要很多步骤、可选参数或复杂流程时
 *  想避免构造函数中传入太多参数（尤其是有些参数是可选的）
 *  想让构建过程更清晰、可读性更好
 */

data class MacBook(
    var cpu: String = "M4",
    var year: String = "2020",
    var size: String = "13",
    var isPro: Boolean = true
)

/**
 * 使用 Scope Function 方便地 build
 */
fun buildByApply(): MacBook = MacBook().apply {
    cpu = "Intel"
    year = "2018"
    size = "15"
    isPro = false
}

/****************** divider ******************/

/**
 * Java Style Builder
 */
class MacBookBuilder {
    private var _cpu: String = ""
    private var _year: String = ""
    private var _size: String = ""
    private var _isPro: Boolean = false

    fun setCPU(cpu: String) = apply { _cpu = cpu }
    fun setYear(year: String) = apply { _year = year }
    fun setSize(size: String) = apply { _size = size }
    fun isPro(isPro: Boolean) = apply { _isPro = isPro }

    fun build(): MacBook = MacBook(
        _cpu, _year, _size, _isPro
    )
}

fun buildByBuilder(): MacBook =
    MacBookBuilder()
        .setCPU("M2")
        .setSize("13.3")
        .setYear("2024")
        .isPro(true)
        .build()

/****************** divider ******************/

@DslMarker
annotation class MacBookDSL

@MacBookDSL
class MacBookDSLBuilder {
    var cpu: String = "M4"
    var year: String = "2020"
    var size: String = "13"
    var isPro: Boolean = true

    fun build(): MacBook = MacBook(cpu, year, size, isPro)
}

fun macbook(block: MacBookDSLBuilder.() -> Unit): MacBook {
    val builder = MacBookDSLBuilder()
    builder.block()
    return builder.build()
}

fun buildByDSL(): MacBook = macbook {
    cpu = "M1"
    year = "2020"
    size = "15"
    isPro = false
}

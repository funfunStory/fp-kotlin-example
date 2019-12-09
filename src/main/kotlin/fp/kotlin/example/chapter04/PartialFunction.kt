package fp.kotlin.example.chapter04

fun main() {
    println(twice(5))
    println(partialTwice(105))

    testPartialFunction1()
    testPartialFunction2()
    testToPartialFunction()
}

private fun twice(x: Int) = x * 2

private fun partialTwice(x: Int): Int =
    if (x < 100) {
        x * 2
    } else {
        throw IllegalArgumentException()
    }

private fun sayNumber1(x: Int): String = when (x) {
    1 -> "One!"
    2 -> "Two!"
    3 -> "Three!"
    else -> "Not between 1 and 3"
}

private fun sayNumber2(x: Int): String = when (x) {
    1 -> "One!"
    2 -> "Two!"
    3 -> "Three!"
    else -> throw IllegalArgumentException()
}

class PartialFunction<in P, out R>(
    private val condition: (P) -> Boolean,
    private val f: (P) -> R)
    : (P) -> R {

    override fun invoke(p: P): R = when {
        condition(p) -> f(p)
        else -> throw IllegalArgumentException("$p isn't supported.")
    }

    fun isDefinedAt(p: P): Boolean = condition(p)
}

private fun testPartialFunction1() {
    val condition: (Int) -> Boolean = { it in 1..3 }
    val body: (Int) -> String = {
        when (it) {
            1 -> "One!"
            2 -> "Two!"
            3 -> "Three!"
            else -> "Not between 1 and 3"
        }
    }

    val oneTwoThree = PartialFunction(condition, body)
    if (oneTwoThree.isDefinedAt(3)) {
        println(oneTwoThree(3))
    } else {
        println("isDefinedAt(x) return false")
    }
}

private fun testPartialFunction2() {
    val isEven = PartialFunction<Int, String>({ 0 == it % 2 }, { "$it is even" })

    if (isEven.isDefinedAt(100)) {
        println(isEven(100))     // "100 is even"
    } else {
        println("isDefinedAt(x) return false")
    }
}

fun <P, R> ((P) -> R).toPartialFunction(definedAt: (P) -> Boolean)
    : PartialFunction<P, R> = PartialFunction(definedAt, this)

fun testToPartialFunction() {
    val condition: (Int) -> Boolean = { 0 == it.rem(2) }
    val body: (Int) -> String = { "$it is even" }

    val isEven = body.toPartialFunction(condition)

    if (isEven.isDefinedAt(100)) {
        println(isEven(100))     // "100 is even!"
    } else {
        println("isDefinedAt(x) return false")
    }
}
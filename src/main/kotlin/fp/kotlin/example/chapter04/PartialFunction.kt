package fp.kotlin.example.chapter04

fun main(args: Array<String>) {
    testPartialFunction()
    testToPartialFunction()
    testOrElse()
    testInvokeOrElse()
}

fun sayNumber(x: Int): String {
    return when (x) {
        1 -> "One!"
        2 -> "Two!"
        3 -> "Three!"
        else -> throw IllegalArgumentException()
    }
}

fun demo1() {
    val condition: (Int) -> Boolean = { it in 1..3}
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
        print(oneTwoThree(3))
    } else {
        print("isDefinedAt(x) return false")
    }
}

val condition: (Int) -> Boolean = { 0 == it.rem(2) }
val body: (Int) -> String = { "$it is even" }

fun testPartialFunction() {
    val isEven = PartialFunction<Int, String>({ 0 == it % 2 }, { "$it is even" })

    if (isEven.isDefinedAt(100)) {
        print(isEven(100))     // "100 is even"
    } else {
        print("isDefinedAt(x) return false")
    }
}

fun testToPartialFunction() {
    val isEven = body.toPartialFunction(condition)

    if (isEven.isDefinedAt(100)) {
        print(isEven(100))     // "100 is even!"
    } else {
        print("isDefinedAt(x) return false")
    }
}

fun testOrElse() {
    val isEven = body.toPartialFunction(condition)
    val isOdd = { i: Int -> "$i is odd" }.toPartialFunction{ !condition(it) }

    // [1 is odd, 2 is even, 3 is odd]
    print(listOf(1, 2, 3).map( isEven orElse isOdd ))
}

fun testInvokeOrElse() {
    val isEven = body.toPartialFunction(condition)

    // [1 is odd, 2 is even, 3 is odd]
    print(listOf(1, 2, 3).map { isEven.invokeOrElse(it, "$it is odd") })
}

class PartialFunction<P, R>(
        private val condition: (P) -> Boolean,
        private val f: (P) -> R)
    :(P) -> R {

    override fun invoke(p: P): R {
        if (condition(p)) {
            return f(p)
        } else {
            throw IllegalArgumentException("$p isn't supported.")
        }
    }

    fun isDefinedAt(p: P): Boolean = condition(p)

    fun invokeOrElse(p: P, default: R): R {
        return if (isDefinedAt(p)) invoke(p) else default
    }

    infix fun orElse(that: PartialFunction<P, R>): PartialFunction<P, R> {
        return PartialFunction({ this.isDefinedAt(it) || that.isDefinedAt(it) }) {
            when {
                this.isDefinedAt(it) -> this(it)
                that.isDefinedAt(it) -> that(it)
                else -> throw IllegalArgumentException("$it isn't defined")
            }
        }
    }
}

fun <P, R> ((P) -> R).toPartialFunction(definedAt: (P) -> Boolean): PartialFunction<P, R> {
    return PartialFunction(definedAt, this)
}



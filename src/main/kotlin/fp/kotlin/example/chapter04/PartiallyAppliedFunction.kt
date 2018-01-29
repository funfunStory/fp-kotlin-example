package fp.kotlin.example.chapter04

fun main(args: Array<String>) {
    val func = { a: String, b: String -> a + b }

    val partiallyAppliedFunc1 = func.partial1("Hello")
    val result1 = partiallyAppliedFunc1("World")

    println(result1)  // Hello World

    val partiallyAppliedFunc2 = func.partial2("World")
    val result2 = partiallyAppliedFunc2("Hello")

    println(result2)  // Hello World
}

fun <P1, P2, R> ((P1, P2) -> R).partial1(p1: P1): (P2) -> R {
    return { p2 -> this(p1, p2) }
}

fun <P1, P2, R> ((P1, P2) -> R).partial2(p2: P2): (P1) -> R {
    return { p1 -> this(p1, p2) }
}
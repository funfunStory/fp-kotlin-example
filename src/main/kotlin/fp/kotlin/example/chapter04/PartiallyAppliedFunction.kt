package fp.kotlin.example.chapter04

fun main(args: Array<String>) {
    val func = { a: String, b: String -> a + b }

    val partiallyAppliedFunc1 = func.partial1("Hello")
    partiallyAppliedFunc1.invoke("World")
    partiallyAppliedFunc1("World")
    val result1 = partiallyAppliedFunc1("World")

    println(result1)  // "Hello World" 출력

    val partiallyAppliedFunc2 = func.partial2("World")
    val result2 = partiallyAppliedFunc2("Hello")

    println(result1)  // "Hello World" 출력
}

fun <P1, P2, R> ((P1, P2) -> R).partial1(p1: P1): (P2) -> R {
    return { p2 -> this(p1, p2) }
}

fun <P1, P2, R> ((P1, P2) -> R).partial2(p2: P2): (P1) -> R {
    return { p1 -> this(p1, p2) }
}
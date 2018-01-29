package fp.kotlin.example.chapter04.solution

/**
 * 연습문제 4-2
 *
 * 매개변수 3개를 받는 부분 적용 함수 3개를 직접 구현하라.
 */

fun main(args: Array<String>) {
    val func = { a: Int, b: Int, c: Int -> a + b + c }

    val partiallyAppliedFunc1 = func.partial1(1)
    println(partiallyAppliedFunc1(2, 3))  // 6

    val partiallyAppliedFunc2 = func.partial2(2)
    println(partiallyAppliedFunc2(1, 3))  // 6

    val partiallyAppliedFunc3 = func.partial3(3)
    println(partiallyAppliedFunc3(1, 2))  // 6
}

private fun <P1, P2, P3, R> ((P1, P2, P3) -> R).partial1(p1: P1): (P2, P3) -> R {
    return { p2, p3 -> this(p1, p2, p3) }
}

private fun <P1, P2, P3, R> ((P1, P2, P3) -> R).partial2(p2: P2): (P1, P3) -> R {
    return { p1, p3 -> this(p1, p2, p3) }
}

private fun <P1, P2, P3, R> ((P1, P2, P3) -> R).partial3(p3: P3): (P1, P2) -> R {
    return { p1, p2 -> this(p1, p2, p3) }
}
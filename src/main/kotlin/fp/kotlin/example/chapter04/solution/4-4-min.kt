package fp.kotlin.example.chapter04.solution

/**
 * 연습문제 4-4
 *
 * ``min`` 함수를 ``curried`` 함수를 사용해서 작성하라.
 */

fun main() {
    val curriedMin = min.curried()
    println(curriedMin(10)(30))    // 10
}

private val min = { a: Int, b: Int -> if (a <= b) a else b }

fun <P1, P2, R> ((P1, P2) -> R).curried(): (P1) -> (P2) -> R = { p1: P1 -> { p2: P2 -> this(p1, p2) } }
package fp.kotlin.example.chapter04.solution

/**
 * 연습문제 4-4
 *
 * 연습문제 4-3에서 작성한 ``max`` 함수를 ``curried`` 함수를 만들어서 재작성하라.
 */

fun main(args: Array<String>) {
    println(max(10)(30))    // 30
}

private val max = { a: Int, b: Int -> if (a >= b) a else b }.curried()

fun <P1, P2, R> ((P1, P2) -> R).curried(): (P1) -> (P2) -> R = { p1: P1 -> { p2: P2 -> this(p1, p2) } }
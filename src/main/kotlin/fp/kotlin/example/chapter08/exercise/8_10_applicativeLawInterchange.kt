package fp.kotlin.example.chapter08.exercise

/**
 *
 * 연습문제 8-10
 *
 * 연습문제 8-3 에서 만든 리스트 애플리케이티브 펑터가 교환 법칙을 만족하는지 확인해보자.
 *
 */

fun main() {

    val function: (Int) -> Int = { x: Int -> x * 10 }
    val value = 10

    val left = TODO()
    val right = TODO()

    require(left == right)
}

private fun <T, R> of(value: T) = { f: (T) -> R -> f(value) }
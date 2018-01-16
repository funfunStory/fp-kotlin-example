package fp.kotlin.example.chapter03.exercise

/**
 * 연습문제 3-11
 *
 * 연습문제 3-10에서 작성한 Factorial 함수를 FP적인 방법으로 성능을 개선해보라.
 */

fun main(args: Array<String>) {
    require(1 == factorial(1))
    require(24 == factorial(4))
    require(5040 == factorial(7))
    require(3628800 == factorial(10))
}

private fun factorial(n: Int, acc: Int = 1): Int = TODO()
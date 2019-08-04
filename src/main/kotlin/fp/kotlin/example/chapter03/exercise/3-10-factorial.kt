package fp.kotlin.example.chapter03.exercise

/**
 * 연습문제 3-10
 *
 * 연습문제 3-3에서 작성한 ``factorial`` 함수를 메모이제이션을 사용해서 개선해 보라.
 */

fun main(args: Array<String>) {
    require(1 == factorial(1))
    require(24 == factorial(4))
    require(5040 == factorial(7))
    require(3628800 == factorial(10))
}

private fun factorial(n: Int): Int = TODO()
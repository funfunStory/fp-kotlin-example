package fp.kotlin.example.chapter03.exercise

/**
 * 연습문제 3-3
 *
 * 입력 n의 팩토리얼(Factorial)인 n!를 구하는 함수를 재귀로 구현해 보자.
 */

fun main() {
    require(1 == factorial(1))
    require(24 == factorial(4))
    require(5040 == factorial(7))
    require(3628800 == factorial(10))
}

private fun factorial(n: Int): Int = TODO()
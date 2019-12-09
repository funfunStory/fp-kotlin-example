package fp.kotlin.example.chapter03.solution

/**
 * 연습문제 3-3
 *
 * 입력 n의 팩토리얼(Factorial)인 n!를 구하는 함수를 재귀로 구현해 보자.
 */

fun main() {
    println(factorial(1))   // 1
    println(factorial(4))   // 24
    println(factorial(7))   // 5040
    println(factorial(10))  // 3628800
}

private fun factorial(n: Int): Int = when (n) {
    0 -> 1
    else -> n * factorial(n - 1)
}
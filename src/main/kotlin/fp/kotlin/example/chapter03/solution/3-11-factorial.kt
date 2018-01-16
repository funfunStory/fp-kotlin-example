package fp.kotlin.example.chapter03.solution

/**
 * 연습문제 3-11
 *
 * 연습문제 3-10에서 작성한 Factorial 함수를 FP적인 방법으로 성능을 개선해보라.
 */

fun main(args: Array<String>) {
    println(factorial(1))   // 1
    println(factorial(4))   // 24
    println(factorial(7))   // 5040
    println(factorial(10))  // 3628800
}

private fun factorial(n: Int, acc: Int = 1): Int = when (n) {
    0 -> acc
    else -> factorial(n - 1, n * acc)
}
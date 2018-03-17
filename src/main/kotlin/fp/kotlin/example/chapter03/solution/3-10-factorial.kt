package fp.kotlin.example.chapter03.solution

/**
 * 연습문제 3-10
 *
 * 연습문제 3-3에서 작성한 Factorial 함수를 메모이제이션을 사용해서 개선해보라.
 */

fun main(args: Array<String>) {
    println(factorial(1))   // 1
    println(factorial(4))   // 24
    println(factorial(7))   // 5040
    println(factorial(10))  // 3628800
}

private var memo = Array(100, { -1 })

private fun factorial(n: Int): Int = when {
    0 == n -> 1
    memo[n] != -1 -> memo[n]
    else -> {
        memo[n] = n * factorial(n - 1)
        memo[n]
    }
}
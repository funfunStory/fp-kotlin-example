package fp.kotlin.example.chapter03.solution

/**
 * 연습문제 3-2
 *
 * X의 n 승을 구하는 함수를 재귀로 구현해보자.
 *
 * 힌트: 함수의 선언 타입은 아래와 같다.
 * fun power(x: Double, n: Int): Double
 */

fun main(args: Array<String>) {
    println(power(5.0, 2))   // 25.0
    println(power(2.0, 10))  // 1024.0
}

private fun power(x: Double, n: Int): Double = when (n) {
    0 -> 1.0
    else -> x * power(x, n - 1)
}
package fp.kotlin.example.chapter03.exercise

/**
 * 연습문제 3-2
 *
 * X의 n 승을 구하는 함수를 재귀로 구현해보자.
 *
 * 힌트: 함수의 선언 타입은 아래와 같다.
 * fun power(x: Double, n: Int): Double
 */

fun main(args: Array<String>) {
    require(25.0 == power(5.0, 2))
    require(1024.0 == power(2.0, 10))
}

private fun power(x: Double, n: Int): Double = TODO()
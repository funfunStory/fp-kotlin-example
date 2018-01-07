package fp.kotlin.example.chapter03.exercise

/**
 * 연습문제 3-17
 *
 * 입력값 n의 제곱근을 2로 나눈 값이 1보다 작을때까지 반복하고 최초의 1보다 작은 값을 반환하는 함수를 상호 재귀를 사용하여 구현하라.
 *
 * 힌트:
 * 1) 제곱근을 구하는 함수와 2로 나누는 함수를 쪼개라.
 * 2) 제곱근은 java.lang.Math.sqrt() 함수를 사용하여 구할 수 있다.
 */

fun main(args: Array<String>) {
    require(0.528685631720282 == squareRoot(5.0))
}

private fun squareRoot(n: Double): Double = TODO()

private fun divideTwo(n: Double): Double  = TODO()
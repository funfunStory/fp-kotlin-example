package fp.kotlin.example.chapter03.exercise

/**
 * 연습문제 3-9
 *
 * 최대공약수를 구하는 gcd 함수를 작성해보자.
 *
 * 힌트: 함수의 선언 타입은 아래와 같다.
 * fun gcd(m: Int, n: Int): Int
 */

fun main() {
    require(6 == gcd(12, 18))
    require(12 == gcd(60, 48))
    require(6 == gcd(366, 60))
}

private fun gcd(m: Int, n: Int): Int = TODO()
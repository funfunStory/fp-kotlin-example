package fp.kotlin.example.chapter03.solution

/**
 * 연습문제 3-9
 *
 * 최대공약수를 구하는 gcd 함수를 작성해보자.
 *
 * 힌트: 함수의 선언 타입은 아래와 같다.
 * fun gcd(m: Int, n: Int): Int
 */

fun main(args: Array<String>) {
    println(gcd(12, 18))    // 6
    println(gcd(60, 48))    // 12
    println(gcd(366, 60))   // 6
}

fun gcd(m: Int, n: Int): Int = when (n) {
    0 -> m
    else -> gcd(n, m % n)
}
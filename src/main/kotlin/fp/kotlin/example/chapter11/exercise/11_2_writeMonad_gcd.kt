package fp.kotlin.example.chapter11.exercise

import fp.kotlin.example.chapter10.Maybe
import fp.kotlin.example.chapter10.Nothing
import fp.kotlin.example.chapter10.exercise.FunStream
import fp.kotlin.example.chapter10.exercise.funStreamOf
import fp.kotlin.example.chapter11.logging.WriterMonad

/**
 *
 * 연습문제 11-2
 *
 * 최대공약수를 구하는 유클리드 알고리즘(Euclidean algorithm)의 동작 로그를 살펴볼 수 있는 함수 ``gcd``를 WriterMonad를 사용해서 만들어보자.
 *
 * 힌트 : ``gcd(60, 48)``를 호출했을때 아래와 같이 출력되어야 한다.
 * [60 mod 48 = 12, 48 mod 12 = 0, Finished with 12]
 *
 */

fun main() {
    require(gcd(60, 48) == funStreamOf("60 mod 48 = 12", "48 mod 12 = 0", "Finished with 12"))
    require(gcd2(60, 48) == funStreamOf("60 mod 48 = 12", "48 mod 12 = 0", "Finished with 12"))
    require(gcd(48, 60) == funStreamOf("48 mod 60 = 48", "60 mod 48 = 12", "48 mod 12 = 0", "Finished with 12"))
    require(gcd2(48, 60) == funStreamOf("48 mod 60 = 48", "60 mod 48 = 12", "48 mod 12 = 0", "Finished with 12"))
    require(gcd(60, 5) == funStreamOf("60 mod 5 = 0", "Finished with 5"))
    require(gcd2(60, 5) == funStreamOf("60 mod 5 = 0", "Finished with 5"))
    require(gcd(61, 3) == funStreamOf("61 mod 3 = 1", "3 mod 1 = 0", "Finished with 1"))
    require(gcd2(61, 3) == funStreamOf("61 mod 3 = 1", "3 mod 1 = 0", "Finished with 1"))
    require(gcd(63, 24) == funStreamOf("63 mod 24 = 15", "24 mod 15 = 9", "15 mod 9 = 6", "9 mod 6 = 3", "6 mod 3 = 0",
        "Finished with 3"))
    require(gcd2(63, 24) == funStreamOf("63 mod 24 = 15", "24 mod 15 = 9", "15 mod 9 = 6", "9 mod 6 = 3", "6 mod 3 = 0",
        "Finished with 3"))

}

fun gcd(x: Int, y: Int,
    maybeWriteMonad: Maybe<WriterMonad<Pair<Int, Int>>> = Nothing): FunStream<String> = TODO()

fun gcd2(x: Int, y: Int, writeMonad: WriterMonad<Pair<Int, Int>>? = null): FunStream<String> = TODO()

private infix fun <T> T.withLog(log: String): WriterMonad<T> = TODO()
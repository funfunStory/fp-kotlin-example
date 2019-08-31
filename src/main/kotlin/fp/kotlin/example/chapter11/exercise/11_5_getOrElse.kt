package fp.kotlin.example.chapter11.exercise

import fp.kotlin.example.chapter11.exception.*

/**
 * 연습문제 11-5
 *
 * 트라이의 상태가 ``Success``일 경우에는 ``value``를 반환하고, ``Failure``인 경우에는 지정한 기본값(default)을 반환하는 확장 함수
 * ``getOrElse(default)``를 만들어 보자.
 */
fun main() {
    val result1 = Try.pure(10).fmap { it / 0 }.getOrElse(100)
    val result2 = Try.pure(10).fmap { it / 5 }.getOrElse(100)

    require(result1 == 100)
    require(result2 == 2)
}

fun <T> Try<T>.getOrElse(default: T): T = TODO()
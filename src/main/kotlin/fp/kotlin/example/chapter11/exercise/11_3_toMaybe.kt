package fp.kotlin.example.chapter11.exercise

import fp.kotlin.example.chapter10.Just
import fp.kotlin.example.chapter10.Maybe
import fp.kotlin.example.chapter10.Nothing
import fp.kotlin.example.chapter11.exception.Failure
import fp.kotlin.example.chapter11.exception.Success
import fp.kotlin.example.chapter11.exception.Try

/**
 * 연습문제 11-3
 *
 * ``Try``를 ``Maybe``로 변환해 주는 확장함수 ``toMaybe``를 만들어 보자.
 *
 * 힌트 : 트라이가 ``Success``일 경우에는 ``Just``를, ``Failure``인 경우에는 ``Nothing``으로 변환한다.
 */
fun main() {
    val result1 = Try.pure(10).fmap { it / 0 }.toMaybe()
    val result2 = Try.pure(10).fmap { it / 5 }.toMaybe()

    require(result1 is Nothing)
    require(result2 is Just)
}

fun <T> Try<T>.toMaybe(): Maybe<T> = TODO()

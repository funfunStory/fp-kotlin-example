package fp.kotlin.example.chapter11.exercise

import fp.kotlin.example.chapter10.Just
import fp.kotlin.example.chapter10.Maybe
import fp.kotlin.example.chapter10.Nothing
import fp.kotlin.example.chapter11.exception.Failure
import fp.kotlin.example.chapter11.exception.Success
import fp.kotlin.example.chapter11.exception.Try

/**
 *
 * 연습문제 11-3
 *
 * Try를 Maybe 로 변환해주는 변환함수 toMaybe 확장함수를 만들어보자.
 *
 * 힌트 : Tyr가 Success일 경우에는 Just를 Failure인 경우에는 Nothing으로 변환한다.
 *
 */

fun main() {
    val result1 = Try.pure(10).fmap { it / 0 }.toMaybe()
    val result2 = Try.pure(10).fmap { it / 5 }.toMaybe()

    require(result1 is Nothing)
    require(result2 is Just)
}

fun <T> Try<T>.toMaybe(): Maybe<T> = TODO()

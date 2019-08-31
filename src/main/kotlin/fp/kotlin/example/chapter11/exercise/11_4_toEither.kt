package fp.kotlin.example.chapter11.exercise

import fp.kotlin.example.chapter11.exception.*

/**
 * 연습문제 11-4
 *
 * ``Try``를 ``Either``로 변환해 주는 변환함수 ``toEither`` 확장함수를 만들어 보자.
 *
 * 힌트 : 트라이가 ``Success``일 경우에는 ``Right``를, ``Failure``인 경우에는 ``Left``로 변환한다.
 */
fun main() {
    val result1 = Try.pure(10).fmap { it / 0 }.toEither()
    val result2 = Try.pure(10).fmap { it / 5 }.toEither()

    require(result1 is Left)
    require(result2 is Right)
}

fun <T> Try<T>.toEither(): Either<Throwable, T> = TODO()

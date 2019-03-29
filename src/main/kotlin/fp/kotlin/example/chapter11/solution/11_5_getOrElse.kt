package fp.kotlin.example.chapter11.solution

import fp.kotlin.example.chapter11.exception.*

/**
 *
 * 연습문제 11-5
 *
 * Try의 값이 Success일 경우에는 value를 가져오고 Failure인 경우에는 지정한 default 값을 가져오는 확장함수 getOrElse(default) 함수를 만들어보자.
 *
 */
fun main() {
    val result1 = Try.pure(10).fmap { it / 0 }.getOrElse(100)
    val result2 = Try.pure(10).fmap { it / 5 }.getOrElse(100)

    require(result1 == 100)
    require(result2 == 2)
}

fun <T> Try<T>.getOrElse(default: T): T = when(this){
    is Success -> this.value
    is Failure -> default
}

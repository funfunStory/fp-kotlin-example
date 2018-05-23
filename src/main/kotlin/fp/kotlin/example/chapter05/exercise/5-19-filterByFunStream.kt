package fp.kotlin.example.chapter05.exercise

import fp.kotlin.example.chapter05.FunStream
import fp.kotlin.example.chapter05.funStreamOf
import fp.kotlin.example.chapter05.getHead
import fp.kotlin.example.chapter05.toFunStream

/**
 *
 * 연습문제 5-19
 *
 * FunList에서 작성했던 filter 함수를 FunStream에도 추가하자.
 *
 * 힌트: 함수의 선언 타입은 아래와 같다.
 *
 */

fun main(args: Array<String>) {
    require(funStreamOf(1, 2, 3, 4, 5)
        .filter { it % 2 == 0 } == funStreamOf(2, 4))
    require(funStreamOf(1, 2, 3, 4, 5)
        .filter { it > 6 } == FunStream.Nil)
    require((1..100000000)
        .toFunStream()
        .filter { it > 100 }
        .getHead() == 101)
}

fun <T> FunStream<T>.filter(f: (T) -> Boolean): FunStream<T> = TODO()
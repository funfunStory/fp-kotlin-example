package fp.kotlin.example.chapter05.exercise

import fp.kotlin.example.chapter05.FunStream
import fp.kotlin.example.chapter05.funStreamOf

/**
 *
 * 연습문제 5-19
 *
 * FunList에서 작성했던 appendTail 함수를 FunStream에도 추가하자.
 *
 * 힌트: 함수의 선언 타입은 아래와 같다.
 *
 */

fun main(args: Array<String>) {
    require(funStreamOf(1, 2, 3, 4, 5).appendTail(6) == funStreamOf(1, 2, 3, 4, 5, 6))
}

fun <T> FunStream<T>.appendTail(value: T): FunStream<T> = TODO()
package fp.kotlin.example.chapter05.solution

import fp.kotlin.example.chapter05.FunStream
import fp.kotlin.example.chapter05.foldLeft
import fp.kotlin.example.chapter05.funStreamOf
import fp.kotlin.example.chapter05.toFunStream

/**
 *
 * 연습문제 5-16
 *
 * FunList에서 작성했던 sum 함수를 FunStream에도 추가하자.
 *
 * 힌트: 함수의 선언 타입은 아래와 같다.
 *
 */

fun main(args: Array<String>) {
    require(funStreamOf(1, 2, 3, 4, 5).sum() == 1 + 2 + 3 + 4 + 5)
    require((1..10000).toFunStream().sum() == 50005000)
}

fun FunStream<Int>.sum(): Int = this.foldLeft(0) { acc, value -> acc + value }
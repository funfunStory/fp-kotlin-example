package fp.kotlin.example.chapter05.exercise

import fp.kotlin.example.chapter05.FunList
import fp.kotlin.example.chapter05.funListOf

/**
 *
 * 연습문제 5-22
 *
 * FunList에 toString 함수를 추가해보자.
 *
 * 힌트: 함수의 선언 타입은 아래와 같다.
 *
 */

fun main(args: Array<String>) {
    require(funListOf(1, 2, 3, 4, 5).toString("") == "[1, 2, 3, 4, 5]")
}

tailrec fun <T> FunList<T>.toString(acc: String): String = TODO()
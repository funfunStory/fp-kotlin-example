package fp.kotlin.example.chapter05.solution

import fp.kotlin.example.chapter05.FunList
import fp.kotlin.example.chapter05.funListOf

/**
 *
 * 연습문제 5-24
 *
 * FunList에 printFunList 함수를 추가해보자.
 *
 * 힌트: 함수의 선언 타입은 아래와 같다.
 *
 */

fun main(args: Array<String>) {
    require(funListOf(1, 2, 3, 4, 5).printlnFunList() == "[1, 2, 3, 4, 5]")
}

tailrec fun <T> FunList<T>.printlnFunList(acc: String = ""): String = when (this) {
    FunList.Nil -> "[${acc.drop(2)}]"
    is FunList.Cons -> tail.printlnFunList("$acc, $head")
}
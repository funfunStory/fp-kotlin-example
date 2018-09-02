package fp.kotlin.example.chapter05.solution

import fp.kotlin.example.chapter05.FunList
import fp.kotlin.example.chapter05.FunList.Cons
import fp.kotlin.example.chapter05.FunList.Nil

/**
 *
 * 연습문제 5-3
 *
 * 리스트의 첫번째 값을 가져오는 getHead 함수를 작성해 보자.
 *
 * 힌트: 함수의 선언 타입은 아래와 같다.
 *      빈 리스트일 경우에는 NullPointerException 을 발생시키자.
 *
 */

fun main(args: Array<String>) {

    val intList = Cons(1, Cons(2, Cons(3, Nil)))
    require(intList.getHead() == 1)

}

fun <T> FunList<T>.getHead(): T = when (this) {
    Nil -> throw NullPointerException()
    is Cons -> this.head
}
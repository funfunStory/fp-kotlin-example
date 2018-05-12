package fp.kotlin.example.chapter05.solution

import fp.kotlin.example.chapter05.FunList
import fp.kotlin.example.chapter05.FunList.Cons
import fp.kotlin.example.chapter05.FunList.Nil
import fp.kotlin.example.chapter05.getTail

/**
 *
 * 연습문제 5-3
 *
 * 리스트에 마지막 값을 추가하는 appendTail 함수를 작성해 보자. 이때 불변성을 유지하면서, 원본 리스트를 재활용해야 한다.
 *
 * 힌트: 재귀를 활용해야하고, 함수의 선언 타입은 아래와 같다.
 *
 */

fun main(args: Array<String>) {

    val intList = Cons(1, Cons(2, Cons(3, Nil)))
    require(intList.appendTail(4) == Cons(1, Cons(2, Cons(3, Cons(4, Nil)))))

}

fun <T> FunList<T>.appendTail(value: T): FunList<T> = when (this) {
    Nil -> Cons(value, Nil)
    is Cons -> Cons(head, getTail().appendTail(value))
}
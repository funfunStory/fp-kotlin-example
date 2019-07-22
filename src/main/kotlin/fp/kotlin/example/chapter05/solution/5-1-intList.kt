package fp.kotlin.example.chapter05.solution

import fp.kotlin.example.chapter05.FunList
import fp.kotlin.example.chapter05.FunList.Cons
import fp.kotlin.example.chapter05.FunList.Nil

/**
 *
 * 연습문제 5-1
 *
 * 구현한 List를 사용해 (1,2,3,4,5)를 갖는 intList를 생성하자.
 *
 */

fun main() {
    val intList: FunList<Int> = Cons(1, Cons(2, Cons(3, Cons(4, Cons(5, Nil)))))
}
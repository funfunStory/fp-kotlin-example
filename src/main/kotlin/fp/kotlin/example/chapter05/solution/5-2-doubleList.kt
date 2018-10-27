package fp.kotlin.example.chapter05.solution

import fp.kotlin.example.chapter05.FunList
import fp.kotlin.example.chapter05.FunList.Cons

/**
 * 연습문제 5-2
 *
 * 구현한 List를 사용해 (1.0, 2.0, 3.0, 4.0, 5.0)를 갖는 doubleList를 생성하자.
 *
 */

fun main(args: Array<String>) {
    val doubleList: FunList<Double> = Cons(1.0, Cons(2.0, Cons(3.0, Cons(4.0, Cons(5.0, FunList.Nil)))))
}
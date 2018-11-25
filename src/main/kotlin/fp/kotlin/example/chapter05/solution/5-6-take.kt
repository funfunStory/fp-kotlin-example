package fp.kotlin.example.chapter05.solution

import fp.kotlin.example.chapter05.FunList
import fp.kotlin.example.chapter05.FunList.Cons
import fp.kotlin.example.chapter05.FunList.Nil
import fp.kotlin.example.chapter05.addHead
import fp.kotlin.example.chapter05.funListOf
import fp.kotlin.example.chapter05.getTail
import fp.kotlin.example.chapter05.reverse

/**
 *
 * 연습문제 5-6
 *
 * 리스트의 앞에서부터 n개의 값을 가진 리스트를 반환하는 take 함수를 구현하자. 이때 원본 리스트가 바뀌지 않고, 새로운 리스트를 반환할때 매번 리스트를 생성하지
 * 않아야 한다.
 *
 * 힌트: 함수의 선언 타입은 아래와 같다.
 *
 */

fun main(args: Array<String>) {

    val intList = Cons(1, Cons(2, Cons(3, Nil)))
    require(intList.take(0) == Nil)
    require(intList.take(1) == funListOf(1))
    require(intList.take(2) == funListOf(1, 2))
    require(intList.take(3) == funListOf(1, 2, 3))
    require(intList.take(4) == funListOf(1, 2, 3))

}

tailrec fun <T> FunList<T>.take(n: Int, acc: FunList<T> = Nil): FunList<T> = when {
    n < 0 -> throw IllegalArgumentException()
    n == 0 || this === Nil -> acc.reverse()
    else -> getTail().take(n - 1, acc.addHead(getHead()))
}

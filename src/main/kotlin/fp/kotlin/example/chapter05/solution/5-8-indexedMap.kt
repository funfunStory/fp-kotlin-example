package fp.kotlin.example.chapter05.solution

import fp.kotlin.example.chapter05.FunList
import fp.kotlin.example.chapter05.FunList.Cons
import fp.kotlin.example.chapter05.FunList.Nil
import fp.kotlin.example.chapter05.addHead
import fp.kotlin.example.chapter05.funListOf
import fp.kotlin.example.chapter05.reverse

/**
 *
 * 연습문제 5-8
 *
 * 앞서 작성한 map 함수에서 고차함수가 값들의 index 값도 같이 받아 올수 있는 indexedMap 함수를 만들자.
 *
 * 힌트: 함수의 선언 타입은 아래와 같다.
 *
 */

fun main() {

    val intList = Cons(1, Cons(5, Cons(3, Cons(2, Nil))))
    require(intList.indexedMap { index, elm -> index * elm } == funListOf(0, 2, 6))
}

tailrec fun <T, R> FunList<T>.indexedMap(index: Int = 0, acc: FunList<R> = Nil, f: (Int, T) -> R): FunList<R> =
    when (this) {
        Nil -> acc.reverse()
        is Cons -> tail.indexedMap(index + 1, acc.addHead(f(index, head)), f)
    }

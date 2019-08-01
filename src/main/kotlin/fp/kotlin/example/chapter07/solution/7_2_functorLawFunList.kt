package fp.kotlin.example.chapter07.solution

import fp.kotlin.example.chapter04.compose

/**
 *
 * 연습문제 7-2
 *
 * 연습문제에서 만들어본 리스트 펑터인 FunList가 펑터의 법칙을 만족하는지 확인해보자.
 */

fun main() {
    val funList: FunList<Int> = Cons(1, Cons(2, Cons(3, Nil)))

    // functor 1 lows
    require(funList == funList.fmap { identity(it) })

    // functor 2 lows
    val add5: (Int) -> Int = { it + 5 }
    val twice: (Int) -> Int = { it * 2 }

    val left = funList.fmap(add5 compose twice)
    val right = funList.fmap(twice).fmap(add5)

    require(left == right)
}

fun <T> identity(value: T): T = value
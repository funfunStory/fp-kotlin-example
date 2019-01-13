package fp.kotlin.example.chapter09.exercise

import fp.kotlin.example.chapter05.FunList
import fp.kotlin.example.chapter05.funListOf

/**
 *
 * 연습문제 9-8
 *
 * FunList 모노이드가 모노이드의 법칙을 만족하는지 테스트해보자.
 *
 */

fun main(args: Array<String>) {

    val x = funListOf(1, 2, 3)
    val y = funListOf(4, 5, 6)
    val z = funListOf(7, 8, 9)

    FunListMonoid<Int>().run {
        require(mappend(x, y) == funListOf(1, 2, 3, 4, 5, 6))
        require(mappend(x, FunList.Nil) == x)
        require(mappend(FunList.Nil, x) == x)
        require(mappend(y, FunList.Nil) == y)
        require(mappend(FunList.Nil, y) == y)
        require(mappend(mappend(x, y), z) == mappend(x, mappend(y, z)))
    }
}
package fp.kotlin.example.chapter09.solution

import fp.kotlin.example.chapter05.FunList
import fp.kotlin.example.chapter05.concat
import fp.kotlin.example.chapter05.funListOf
import fp.kotlin.example.chapter09.Monoid

/**
 *
 * 연습문제 9-7
 *
 * 앞에서 작성한 ``FunList``를 활용해서 리스트 모노이드를 만들어 보자.
 */
fun main(args: Array<String>) {

    val x = funListOf(1, 2, 3)
    val y = funListOf(4, 5, 6)

    FunListMonoid<Int>().run {
        require(mappend(x, y) == funListOf(1, 2, 3, 4, 5, 6))
        require(mappend(x, FunList.Nil) == x)
        require(mappend(FunList.Nil, x) == x)
        require(mappend(y, FunList.Nil) == y)
        require(mappend(FunList.Nil, y) == y)
    }
}

class FunListMonoid<T> : Monoid<FunList<T>> {
    override fun mempty(): FunList<T> = FunList.Nil

    override fun mappend(m1: FunList<T>, m2: FunList<T>): FunList<T> = when (m1) {
        FunList.Nil -> m2
        is FunList.Cons -> m1 concat m2
    }
}
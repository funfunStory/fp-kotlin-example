package fp.kotlin.example.chapter07.exercise

/**
 *
 * 연습문제 7-2
 *
 * 연습문제에서 만들어본 리스트 펑터인 FunList가 펑터의 법칙을 만족하는지 확인해보자.
 */

fun main(args: Array<String>) {
    val funList: FunList<Int> = Cons(1, Cons(2, Cons(3, Nil)))

    // functor 1lows
    require(funList == TODO())

    // functor 2lows
    val left = funList.fmap { TODO() }
    val right = funList.fmap { TODO() }.fmap { TODO() }

    require(left == right)
}

fun <T> identity(value: T): T = value
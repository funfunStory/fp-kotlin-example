package fp.kotlin.example.chapter09.exercise

import fp.kotlin.example.chapter09.Foldable
import fp.kotlin.example.chapter09.SumMonoid

/**
 *
 * 연습문제 9-10
 *
 * FunList 모노이드를 Foldable 타입클래스의 인스턴스로 만들어서 ``foldLeft`` 함수를 작성하고, ``foldMap`` 함수를 테스트해보자.
 *
 */

fun main(args: Array<String>) {

    val list1 = Cons(1, Cons(2, Cons(3, Cons(4, Nil))))

    require(list1.foldLeft(0) { acc, value -> acc + value } == 10)

    require(list1.foldMap({ x -> x + 1 }, SumMonoid()) == 14)
    require(list1.foldMap({ x -> x * 2 }, SumMonoid()) == 20)
}

sealed class FunList<out T> : Foldable<T>

object Nil : FunList<Nothing>() {
    override fun <B> foldLeft(acc: B, f: (B, Nothing) -> B): B {
        TODO()
    }
}

data class Cons<out T>(val head: T, val tail: FunList<T>) : FunList<T>() {
    override fun <B> foldLeft(acc: B, f: (B, T) -> B): B {
        TODO()
    }
}

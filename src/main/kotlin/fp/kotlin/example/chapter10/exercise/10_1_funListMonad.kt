package fp.kotlin.example.chapter10.exercise

import fp.kotlin.example.chapter10.Monad

/**
 *
 * 연습문제 10-1
 *
 * ``Monad`` 타입클래스의 인스턴스인 ``FunList``를 작성하여 리스트 모나드를 만들어보자.
 *
 * 힌트 : mappend 함수를 사용하자.
 */

fun main() {
    val funList = Cons(1, Cons(2, Cons(3, Nil)))
    val result = funList.flatMap { Cons(it, Cons(it * 2, Cons(it * 3, Nil))) }

    require(result == Cons(1, Cons(2, Cons(3, Cons(2, Cons(4, Cons(6, Cons(3, Cons(6, Cons(9, Nil))))))))))

    val nilList = Nil
    val nilResult = nilList.flatMap { x: Int -> Cons(x, Cons(x * 2, Cons(x * 3, Nil))) }

    require(nilResult == Nil)
}

sealed class FunList<out A> : Monad<A> {

    companion object {
        fun <V> pure(value: V): FunList<V> = TODO()
    }

    override infix fun <V> pure(value: V): Monad<V> = TODO()

    override infix fun <B> flatMap(f: (A) -> Monad<B>): Monad<B> = TODO()

    infix fun <A> FunList<A>.mappend(other: FunList<A>): FunList<A> = TODO()

    infix fun <B> leadTo(m: FunList<B>): FunList<B> = TODO()
}

infix fun <A, B> FunList<(A) -> B>.ap(f: FunList<A>): FunList<B> = TODO()


data class Cons<out A>(val head: A, val tail: FunList<A>) : FunList<A>()

object Nil : FunList<Nothing>()
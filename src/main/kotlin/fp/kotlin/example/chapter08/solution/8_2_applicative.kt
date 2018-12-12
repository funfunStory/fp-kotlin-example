package fp.kotlin.example.chapter08.solution

import fp.kotlin.example.chapter08.Applicative
import fp.kotlin.example.chapter08.solution.FunList.Companion.pure


/**
 *
 * 연습문제 8-2
 *
 * 7장에서 만든 리스트 펑터를 Applicative의 인스턴스로 만들고 테스트해보자.
 *
 */

sealed class FunList<out A> : Applicative<A> {

    companion object {
        fun <V> pure(value: V): Cons<V> = Nil.pure(value)
    }

    abstract override fun <B> fmap(f: (A) -> B): FunList<B>
    override fun <V> pure(value: V): Cons<V> = Cons(value, Nil)
    abstract override fun <B> apply(ff: Applicative<(A) -> B>): FunList<B>
    abstract fun first(): A
    abstract fun size(): Int
}

object Nil : FunList<Nothing>() {

    override fun <B> fmap(f: (Nothing) -> B): FunList<B> = Nil

    override fun <B> apply(ff: Applicative<(Nothing) -> B>): FunList<B> = Nil

    override fun first(): Nothing = throw NoSuchElementException()

    override fun size(): Int = 0
}

data class Cons<A>(val head: A, val tail: FunList<A>) : FunList<A>() {

    override fun <B> fmap(f: (A) -> B): FunList<B> = Cons(f(head), tail.fmap(f))

    override fun <B> apply(ff: Applicative<(A) -> B>): FunList<B> = when (ff) {
        is Cons -> Cons(ff.head(head), tail.apply(ff))
        else -> Nil
    }

    override fun first() = head

    override fun size(): Int = 1 + tail.size()
}


fun main(args: Array<String>) {

    require(pure(1) == Cons(1, Nil))

    require(pure(1).fmap { it * 10 } == Cons(10, Nil))
    require(Nil.fmap { a: Int -> a * 10 } == Nil)

    require(pure(1).fmap { it * 10 } == Cons(10, Nil))
    require(Nil.fmap { a: Int -> a * 10 } == Nil)

    require(pure(1) apply pure { x: Int -> x * 10 } == Cons(10, Nil))
    require(Nil apply pure({ x: Int -> x * 10 }) == Nil)

    require(Cons(1, Cons(2, Cons(3, Cons(4, Nil)))) apply pure { x: Int -> x * 10 } ==
        Cons(10, Cons(20, Cons(30, Cons(40, Nil)))))
}
package fp.kotlin.example.chapter08.exercise

import fp.kotlin.example.chapter08.Applicative
import fp.kotlin.example.chapter08.exercise.FunList.Companion.pure


/**
 *
 * 연습문제 8-2
 *
 * 7장에서 만든 리스트 펑터를 Applicative의 인스턴스로 만들고 테스트해보자.
 *
 */

sealed class FunList<out A> : Applicative<A> {

    companion object {
        fun <V> pure(value: V): Cons<V> = TODO()
    }

    abstract override fun <B> fmap(f: (A) -> B): FunList<B>
    override fun <V> pure(value: V): Cons<V> = Cons(value, Nil)
    abstract override fun <B> apply(ff: Applicative<(A) -> B>): FunList<B>
    abstract fun first(): A
    abstract fun size(): Int
}

object Nil : FunList<Nothing>() {

    override fun <B> fmap(f: (Nothing) -> B): FunList<B> = TODO()

    override fun <B> apply(ff: Applicative<(Nothing) -> B>): FunList<B> = TODO()

    override fun first(): Nothing = TODO()

    override fun size(): Int = TODO()
}

data class Cons<A>(val head: A, val tail: FunList<A>) : FunList<A>() {

    override fun <B> fmap(f: (A) -> B): FunList<B> = TODO()

    override fun <B> apply(ff: Applicative<(A) -> B>): FunList<B> = TODO()

    override fun first() = TODO()

    override fun size(): Int = TODO()
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
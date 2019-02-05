package fp.kotlin.example.chapter08.exercise

import fp.kotlin.example.chapter08.Applicative
import fp.kotlin.example.chapter08.exercise.AFunList.Companion.pure


/**
 *
 * 연습문제 8-2
 *
 * 7장에서 만든 리스트 펑터를 Applicative의 인스턴스로 만들고 테스트해보자.
 *
 */

sealed class AFunList<out A> : Applicative<A> {

    companion object {
        fun <V> pure(value: V): ACons<V> = TODO()
    }

    abstract override fun <B> fmap(f: (A) -> B): AFunList<B>
    override fun <V> pure(value: V): ACons<V> = ACons(value, ANil)
    abstract override fun <B> apply(ff: Applicative<(A) -> B>): AFunList<B>
    abstract fun first(): A
    abstract fun size(): Int
}

object ANil : AFunList<Nothing>() {

    override fun <B> fmap(f: (Nothing) -> B): AFunList<B> = TODO()

    override fun <B> apply(ff: Applicative<(Nothing) -> B>): AFunList<B> = TODO()

    override fun first(): Nothing = TODO()

    override fun size(): Int = TODO()
}

data class ACons<A>(val head: A, val tail: AFunList<A>) : AFunList<A>() {

    override fun <B> fmap(f: (A) -> B): AFunList<B> = TODO()

    override fun <B> apply(ff: Applicative<(A) -> B>): AFunList<B> = TODO()

    override fun first() = TODO()

    override fun size(): Int = TODO()
}


fun main(args: Array<String>) {

    require(pure(1) == ACons(1, ANil))

    require(pure(1).fmap { it * 10 } == ACons(10, ANil))
    require(ANil.fmap { a: Int -> a * 10 } == ANil)

    require(pure(1).fmap { it * 10 } == ACons(10, ANil))
    require(ANil.fmap { a: Int -> a * 10 } == ANil)

    require(pure(1) apply pure { x: Int -> x * 10 } == ACons(10, ANil))
    require(ANil apply pure({ x: Int -> x * 10 }) == ANil)

    require(ACons(1, ACons(2, ACons(3, ACons(4, ANil)))) apply pure { x: Int -> x * 10 } ==
        ACons(10, ACons(20, ACons(30, ACons(40, ANil)))))
}
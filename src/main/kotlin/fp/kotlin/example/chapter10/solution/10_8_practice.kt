package fp.kotlin.example.chapter10.solution

import fp.kotlin.example.chapter10.Monad

/**
 *
 * 연습문제 10-8
 *
 * 새로운 FunList와 동일한 유사한 인터페이스의 FunStream를 만들어 보자.
 * 힌트: 5장에서 작성한 FunStream을 참고하라.
 */
fun main() {

    val funStream: FunStream<Int> = FunStream.Cons({ 1 },
        { FunStream.Cons({ 2 }, { FunStream.Cons({ 3 }, { FunStream.Nil }) }) })
    val result = funStream.flatMap {
        FunStream.Cons({ it }, { FunStream.Cons({ it * 2 }, { FunStream.Cons({ it * 3 }, { FunStream.Nil }) }) })
    }

    require(result == FunStream.Cons({ 1 }, {
        FunStream.Cons({ 2 }, {
            FunStream.Cons({ 3 }, {
                FunStream.Cons({ 2 }, {
                    FunStream.Cons({ 4 }, {
                        FunStream.Cons({ 6 }, {
                            FunStream.Cons({ 3 },
                                { FunStream.Cons({ 6 }, { FunStream.Cons({ 9 }, { FunStream.Nil }) }) })
                        })
                    })
                })
            })
        })
    }))

    val nilStream = FunStream.Nil
    val nilResult = nilStream.flatMap { x: Int ->
        FunStream.Cons({ x }, { FunStream.Cons({ x * 2 }, { FunStream.Cons({ x * 3 }, { FunStream.Nil }) }) })
    }

    require(nilResult == FunStream.Nil)

}

sealed class FunStream<out A> : Monad<A> {

    object Nil : FunStream<Nothing>()
    data class Cons<out A>(val head: () -> A, val tail: () -> FunStream<A>) : FunStream<A>() {
        override fun equals(other: Any?): Boolean =
            if (other is Cons<*>) {
                if (head() == other.head()) {
                    tail() == other.tail()
                } else {
                    false
                }
            } else {
                false
            }

        override fun hashCode(): Int {
            var result = head.hashCode()
            result = 31 * result + tail.hashCode()
            return result
        }
    }

    companion object {
        infix fun <V> pure(value: V): FunStream<V> = Cons({ 0 }, { Nil }).pure(value) as FunStream<V>
    }

    override infix fun <V> pure(value: V): Monad<V> = when (this) {
        FunStream.Nil -> this as FunStream<V>
        is FunStream.Cons -> Cons({ value }, { Nil })
    }

    override infix fun <B> flatMap(f: (A) -> Monad<B>): Monad<B> = when (this) {
        FunStream.Nil -> Nil
        is FunStream.Cons -> f(head()) as FunStream<B> mappend tail().flatMap(f) as FunStream<B>
    }

    infix fun <A> FunStream<A>.mappend(other: FunStream<A>): FunStream<A> = when {
        this is FunStream.Nil -> other
        other is FunStream.Nil -> this
        this is FunStream.Cons && other is FunStream.Cons -> FunStream.Cons({ this.head() },
            { this.tail().mappend(other) })
        else -> Nil
    }

    infix fun <B> leadTo(m: FunStream<B>): FunStream<B> = flatMap { m } as FunStream

    infix fun <A, B> FunStream<(A) -> B>.ap(f: FunStream<A>): FunStream<B> = when (this) {
        Nil -> Nil
        is Cons -> f.fmap(head()) as FunStream<B> mappend tail().ap(f)
    }

}
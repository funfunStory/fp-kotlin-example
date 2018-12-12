package fp.kotlin.example.chapter08

import fp.kotlin.example.chapter07.Functor

interface Applicative<out A> : Functor<A> {

    fun <V> pure(value: V): Applicative<V>

    infix fun <B> apply(ff: Applicative<(A) -> B>): Applicative<B>
}
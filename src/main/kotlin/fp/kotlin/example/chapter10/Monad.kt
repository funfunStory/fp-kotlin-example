package fp.kotlin.example.chapter10

import fp.kotlin.example.chapter07.Functor

interface Monad<out A> : Functor<A> {

    fun <V> pure(value: V): Monad<V>

    override fun <B> fmap(f: (A) -> B): Monad<B> = flatMap { a -> pure(f(a)) }

    infix fun <B> flatMap(f: (A) -> Monad<B>): Monad<B>

    infix fun <B> leadTo(m: Monad<B>): Monad<B> = flatMap { m }
}
package fp.kotlin.example.chapter07

import fp.kotlin.example.chapter04.compose

fun main(args: Array<String>) {
    println(JustCounter(10, 3)
            .fmap { it + 10 }
            .fmap { it * 2 }
    )   // JustCounter(40, 5)
    println(NothingCounter.fmap { it: Int -> it + 10 })  // NothingCounter

    // Functor's raws
    println(NothingCounter.fmap { identity(it) } == identity(NothingCounter))   // true
    println(JustCounter(5, 0).fmap { identity(it) } == identity(JustCounter(5, 0))) // false

    val f = { it: Int -> it + 1 }
    val g = { it: Int -> it * 2 }

    val nothingLeft = NothingCounter.fmap { f compose g }
    val nothingRight = NothingCounter.fmap(g).fmap(f)
    println(nothingLeft == nothingRight)    // true

    val justLeft = JustCounter(5, 0).fmap { f compose g }
    val justRight = JustCounter(5, 0).fmap(g).fmap(f)
    println(justLeft == justRight)  // false
}

sealed class MaybeCounter<out A> : Functor<A> {

    abstract override fun toString(): String

    abstract override fun <B> fmap(transform: (A) -> B): MaybeCounter<B>
}

data class JustCounter<out A>(val value: A, val count: Int): MaybeCounter<A>() {

    override fun toString(): String = "JustCounter($value, $count)"

    override fun <B> fmap(transform: (A) -> B): MaybeCounter<B> = JustCounter(transform(value), count + 1)
}

object NothingCounter: MaybeCounter<kotlin.Nothing>() {

    override fun toString(): String = "NothingCounter"

    override fun <B> fmap(transform: (kotlin.Nothing) -> B): MaybeCounter<B> = NothingCounter
}
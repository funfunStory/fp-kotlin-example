package fp.kotlin.example.chapter07

fun main(args: Array<String>) {
    println(Just(10).fmap { it + 10 })   // Just(20)
    println(Nothing.fmap { it: Int -> it + 10 })  // Nothing
}

sealed class Maybe<out A> : Functor<A> {

    abstract fun get(): A

    abstract fun isEmpty(): Boolean

    abstract override fun toString(): String

    abstract override fun <B> fmap(f: (A) -> B): Maybe<B>
}

data class Just<out A>(val value: A) : Maybe<A>() {

    override fun get(): A = value

    override fun isEmpty(): Boolean = false

    override fun toString(): String = "Just($value)"

    override fun <B> fmap(f: (A) -> B): Maybe<B> = Just(f(value))
}

object Nothing : Maybe<kotlin.Nothing>() {

    override fun get(): kotlin.Nothing = throw NoSuchElementException("Has no value")

    override fun isEmpty(): Boolean = true

    override fun toString(): String = "Nothing"

    override fun <B> fmap(f: (kotlin.Nothing) -> B): Maybe<B> = Nothing
}
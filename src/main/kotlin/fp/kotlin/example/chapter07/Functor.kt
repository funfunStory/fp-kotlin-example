package fp.kotlin.example.chapter07

fun main(args: Array<String>) {
    println(maybeOf(10).fmap { it + 10 })   // Just(20)
    println(maybeOf(null))  // Nothing
}

private fun <T> maybeOf(value: T): Maybe<T> = when (value) {
    null -> Nothing
    else -> Just(value)
}

interface Functor<out A> {
    fun <B> fmap(transform: (A) -> B): Functor<B>
}

sealed class Maybe<out A> : Functor<A> {
    
    abstract fun get(): A

    abstract fun isEmpty(): Boolean

    abstract override fun toString(): String

    abstract override fun <B> fmap(transform: (A) -> B): Maybe<B>
}

data class Just<out A>(val value: A) : Maybe<A>() {

    override fun get(): A = value

    override fun isEmpty(): Boolean = false

    override fun toString(): String = "Just($value)"

    override fun <B> fmap(transform: (A) -> B): Maybe<B> = Just(transform(value))
}

object Nothing : Maybe<kotlin.Nothing>() {

    override fun get(): kotlin.Nothing = throw NoSuchElementException("Has no value")

    override fun isEmpty(): Boolean = true

    override fun toString(): String = "Nothing"

    override fun <B> fmap(transform: (kotlin.Nothing) -> B): Maybe<B> = Nothing
}
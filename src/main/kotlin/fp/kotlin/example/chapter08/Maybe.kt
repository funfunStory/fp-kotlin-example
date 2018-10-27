package fp.kotlin.example.chapter08

fun main(args: Array<String>) {
    // fmap test
    println(Just(10).fmap { it + 10 })   // Just(20)
    println(Nothing.fmap { it: Int -> it + 10 })  // Nothing

    // pure test
    println(Maybe.pure(10))  // Just(10)

    // apply test
    println(Just(10) apply Just({ x: Int -> x * 2 }))   // Just(20)
    println(Nothing apply Just({ x: Int -> x * 2 }))    // Nothing
    println(Just(10).apply(Just({ x: Int -> x * 2 })) )   // Just(20)

    // applicative style programming test
    println(Maybe.pure(10)
            apply Just({ x: Int -> x * 2 })
            apply Just({ x: Int -> x + 10 }))    // Just(30)

//    println(Maybe.pure({ x: Int -> x * 2 })
//            apply Just(5)
//            apply Just(10))    // compile error

}

sealed class Maybe<out A> : Applicative<A> {

    companion object {
        fun <V> pure(value: V): Applicative<V> = Just(0).pure(value)
    }

    override fun <V> pure(value: V): Applicative<V> = Just(value)

    abstract override fun <B> apply(ff: Applicative<(A) -> B>): Maybe<B>
}

data class Just<out A>(val value: A) : Maybe<A>() {

    override fun toString(): String = "Just($value)"

    override fun <B> apply(ff: Applicative<(A) -> B>): Maybe<B> = when (ff) {
        is Just -> fmap(ff.value)
        else -> Nothing
    }

    override fun <B> fmap(f: (A) -> B): Maybe<B> = Just(f(value))
}

object Nothing : Maybe<kotlin.Nothing>() {

    override fun toString(): String = "Nothing"

    override fun <B> apply(ff: Applicative<(kotlin.Nothing) -> B>): Maybe<B> = Nothing

    override fun <B> fmap(f: (kotlin.Nothing) -> B): Maybe<B> = Nothing
}
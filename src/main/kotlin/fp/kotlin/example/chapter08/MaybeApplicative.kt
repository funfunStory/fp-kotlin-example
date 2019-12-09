package fp.kotlin.example.chapter08

import fp.kotlin.example.chapter07.Functor

fun main() {
    // fmap test
    println(Just(10).fmap { it + 10 })   // Just(20)
    println(Nothing.fmap { it: Int -> it + 10 })  // Nothing

    // pure test
    println(Maybe.pure(10))  // Just(10)
    println(Maybe.pure { x: Int -> x * 2 })  // Just((kotlin.Int) -> kotlin.Int)

    // apply test
    println(Maybe.pure { x: Int -> x * 2 } apply Just(10))  // Just(20)
    println(Maybe.pure { x: Int -> x * 2 } apply Nothing)   // Nothing

    // applicative style programming test
//    println(AMaybe.pure({ x: Int, y: Int -> x * y}) apply Just(10) apply Just(20))  // compile error

    println(Maybe.pure({ x: Int, y: Int -> x * y }.curried())
            apply Just(10)
            apply Just(20)
    )   // Just(200)

    println(Maybe.pure({ x: Int, y: Int, z: Int -> x * y + z }.curried())
            apply Just(10)
            apply Just(20)
            apply Just(30)
    )   // Just(230)
}

sealed class Maybe<out A> : Functor<A> {

    abstract override fun toString(): String

    abstract override fun <B> fmap(f: (A) -> B): Maybe<B>

    companion object
}

data class Just<out A>(val value: A) : Maybe<A>() {

    override fun toString(): String = "Just($value)"

    override fun <B> fmap(f: (A) -> B): Maybe<B> = Just(f(value))
}

object Nothing : Maybe<kotlin.Nothing>() {

    override fun toString(): String = "Nothing"

    override fun <B> fmap(f: (kotlin.Nothing) -> B): Maybe<B> = Nothing
}

fun <A> Maybe.Companion.pure(value: A) = Just(value)

infix fun <A, B> Maybe<(A) -> B>.apply(f: Maybe<A>): Maybe<B> = when (this) {
    is Just -> f.fmap(value)
    Nothing -> Nothing
}

private fun <P1, P2, R> ((P1, P2) -> R).curried(): (P1) -> (P2) -> R =
        { p1: P1 -> { p2: P2 -> this(p1, p2) } }

private fun <P1, P2, P3, R> ((P1, P2, P3) -> R).curried(): (P1) -> (P2) -> (P3) -> R =
        { p1: P1 -> { p2: P2 -> { p3: P3 -> this(p1, p2, p3) } } }


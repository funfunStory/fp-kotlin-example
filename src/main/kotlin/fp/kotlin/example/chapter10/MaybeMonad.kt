package fp.kotlin.example.chapter10

fun main(args: Array<String>) {
    // fmap test
    println(Just(10).fmap { it + 10 })   // Just(20)
    println(Nothing.fmap { it: Int -> it + 10 })  // Nothing

    // pure test
    println(Maybe.pure(10))  // Just(10)
    println(Maybe.pure({ x: Int -> x * 2 }))  // Just((kotlin.Int) -> kotlin.Int)

    // apply test
    println(Maybe.pure({ x: Int -> x * 2 }) apply Just(10))  // Just(20)
    println(Maybe.pure({ x: Int -> x * 2 }) apply Nothing)   // Nothing
    println(Maybe.pure({ x: Int, y: Int -> x * y }.curried())
            apply Just(10)
            apply Just(20)
    )   // Just(200)

    println(Maybe.pure({ x: Int, y: Int, z: Int -> x * y + z }.curried())
            apply Just(10)
            apply Just(20)
            apply Just(30)
    )   // Just(230)

    // leadTo test
    println(Just(10).leadTo(Nothing))   // Nothing
    println(Nothing.leadTo(Just(10)))   // Nothing
    println(Just(10).leadTo(Just(20)))   // Just(20)

    // flatMap test
    println(Just(10).flatMap { x -> Maybe.pure(x * 2) })    // Just(20)
    println(Nothing.flatMap { x: Int -> Maybe.pure(x * 2) })    // Nothing
    println(Just(Just(10)).flatMap { m -> m.fmap { x -> x * 2 } })  // Just(20)
}

sealed class Maybe<out A> : Monad<A> {

    companion object {
        fun <V> pure(value: V) : Maybe<V> = Just(0).pure(value)
    }

    override fun <B> fmap(f: (A) -> B): Maybe<B> = super.fmap(f) as Maybe<B>

    override fun <V> pure(value: V): Maybe<V> = Just(value)

    override infix fun <B> flatMap(f: (A) -> Monad<B>): Maybe<B> = when (this) {
        is Just -> f(value) as Maybe<B>
        Nothing -> Nothing
    }
}

data class Just<out A>(val value: A) : Maybe<A>() {

    override fun toString(): String = "Just($value)"
}

object Nothing : Maybe<kotlin.Nothing>() {

    override fun toString(): String = "Nothing"
}

infix fun <A, B> Maybe<(A) -> B>.apply(f: Maybe<A>): Maybe<B> = when (this) {
    is Just -> f.fmap(value)
    Nothing -> Nothing
}

private fun <P1, P2, R> ((P1, P2) -> R).curried(): (P1) -> (P2) -> R =
        { p1: P1 -> { p2: P2 -> this(p1, p2) } }

private fun <P1, P2, P3, R> ((P1, P2, P3) -> R).curried(): (P1) -> (P2) -> (P3) -> R =
        { p1: P1 -> { p2: P2 -> { p3: P3 -> this(p1, p2, p3) } } }

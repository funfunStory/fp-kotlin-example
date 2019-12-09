package fp.kotlin.example.chapter08

import fp.kotlin.example.chapter07.Functor

fun main() {
    // fmap test
    println(Right(10).fmap { it * 2 })  // Right(20)
    println(Left("error").fmap { x: String -> "$x log" })   // Left(error)

    // pure test
    println(Either.pure(10))    // Right(10)
    println(Either.pure { x: Int -> x * 2 })   // Right((kotlin.Int) -> kotlin.Int)

    // apply test
    println(Either.pure { x: Int -> x * 2 } apply Left("error"))   // Left(error)
    println(Either.pure { x: Int -> x * 2 } apply Right(10))   // Right(20)
    println(Either.pure({ x: Int, y: Int -> x * y }.curried())
        apply Left("error")
        apply Right(10)
    )   // Left(error)
    println(Either.pure({ x: Int, y: Int -> x * y }.curried())
            apply Right(10)
            apply Right(20)
    )   // Right(200)
}

sealed class Either<out L, out R> : Functor<R> {

    abstract override fun <R2> fmap(f: (R) -> R2): Either<L, R2>

    companion object
}

data class Left<out L>(val value: L) : Either<L, kotlin.Nothing>() {

    override fun toString(): String = "Left($value)"

    override fun <R2> fmap(f: (kotlin.Nothing) -> R2): Either<L, R2> = this

}

data class Right<out R>(val value: R) : Either<kotlin.Nothing, R>() {

    override fun toString(): String = "Right($value)"

    override fun <R2> fmap(f: (R) -> R2): Either<kotlin.Nothing, R2> = Right(f(value))
}

fun <A> Either.Companion.pure(value: A) = Right(value)

infix fun <L, A, B> Either<L, (A) -> B>.apply(f: Either<L, A>): Either<L, B> = when (this) {
    is Left -> this
    is Right -> f.fmap(value)
}

private fun <P1, P2, R> ((P1, P2) -> R).curried(): (P1) -> (P2) -> R =
        { p1: P1 -> { p2: P2 -> this(p1, p2) } }
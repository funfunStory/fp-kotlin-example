package fp.kotlin.example.chapter11.exception

import fp.kotlin.example.chapter10.Monad

fun main(args: Array<String>) {
    // fmap test
    println(Right(10).fmap { it * 2 })  // Right(20)
    println(Left("error").fmap { x: String -> "$x log" })   // Left(error)

    // flatMap test
    println(Right(10).flatMap { x -> Either.pure(x * 2) })  // Right(20)
    println(Left("error").flatMap { x: Int -> Either.pure(x * 2) })   // Left(error)
    println(Right(Right(10)).flatMap { m -> m.fmap { x -> x * 2 } })  // Right(20)
}

sealed class Either<out L, out R> : Monad<R> {

    companion object {
        fun <V> pure(value: V) = Right(0).pure(value)
    }

    override fun <V> pure(value: V): Either<L, V> = Right(value)

    override fun <R2> fmap(f: (R) -> R2): Either<L, R2> = when (this) {
        is Left -> Left(value)
        is Right -> Right(f(value))
    }

    override fun <B> flatMap(f: (R) -> Monad<B>): Monad<B> = when (this) {
        is Left -> Left(value)
        is Right -> f(value)
    }

//    override fun <R2> flatMap(f: (R) -> Monad<R2>): Either<L, R2> = when (this) {
//        is Left -> Left(value)
//        is Right -> try {
//            f(value) as Right<R2>
//        } catch (e: TypeCastException) {
//            Left(e.message) as Left<L>
//        }
//    }
}

data class Left<out L>(val value: L) : Either<L, kotlin.Nothing>() {
    override fun toString(): String = "Left($value)"
}

data class Right<out R>(val value: R) : Either<kotlin.Nothing, R>() {
    override fun toString(): String = "Right($value)"
}

infix fun <L, A, B> Either<L, (A) -> B>.apply(f: Either<L, A>): Either<L, B> = when (this) {
    is Left -> Left(value)
    is Right -> f.fmap(value)
}
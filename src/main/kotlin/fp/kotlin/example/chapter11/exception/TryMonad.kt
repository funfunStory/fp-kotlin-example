package fp.kotlin.example.chapter11.exception

import fp.kotlin.example.chapter10.Monad
import java.lang.NullPointerException

fun main(args: Array<String>) {
    // fmap test
    println(Success(10).fmap { it + 10 })     // Success(20)
    println(Failure(NullPointerException("NPE")).fmap { x: Int -> x + 10 })    // Failure(NPE)

    // pure test
    println(Try.pure(10))    // Success(10)
    println(Try.pure({ x: Int -> x * 2 }))   // Success((kotlin.Int) -> kotlin.Int)

    // apply test
    println(Try.pure({ x: Int -> x * 2 }) apply Failure(NullPointerException("NPE")))   // Failure(NPE)
    println(Try.pure({ x: Int -> x * 2 }) apply Success(10))   // Success(20)
    println(Try.pure({ x: Int, y: Int -> x * y }.curried())
            apply Failure(NullPointerException("NPE"))
            apply Success(10)
    )   // Failure(NPE)
    println(Try.pure({ x: Int, y: Int -> x * y }.curried())
            apply Success(10)
            apply Success(20)
    )   // Success(200)

    // flatMap test
    println(Success(10).flatMap { x -> Try.pure(x * 2) })  // Success(20)
    println(Failure(NullPointerException("NPE")).flatMap {  x: Int -> Try.pure(x * 2) } )   // Failure(NPE)
    println(Success(Success(10)).flatMap {  m -> m.fmap { x -> x * 2 }  })  // Success(20)
}

sealed class Try<out R> : Monad<R> {

    companion object {
        fun <V> pure(value: V) = Success(0).pure(value)
    }

    override fun <B> fmap(f: (R) -> B): Try<B> = super.fmap(f) as Try<B>

    override fun <V> pure(value: V): Try<V> = Success(value)

    override fun <R2> flatMap(f: (R) -> Monad<R2>): Try<R2> = when (this) {
        is Failure -> Failure(e)
        is Success -> try { f(value) as Try<R2> } catch (e: Throwable) { Failure(e) }
    }
}

data class Failure(val e: Throwable) : Try<kotlin.Nothing>() {
    override fun toString(): String = "Failure(${e.message})"
}

data class Success<out R>(val value: R) : Try<R>() {
    override fun toString(): String = "Success($value)"
}

infix fun <T, R> Try<(T) -> R>.apply(f: Try<T>): Try<R> = when (this) {
    is Failure -> Failure(e)
    is Success -> f.fmap(value)
}

private fun <P1, P2, R> ((P1, P2) -> R).curried(): (P1) -> (P2) -> R =
        { p1: P1 -> { p2: P2 -> this(p1, p2) } }
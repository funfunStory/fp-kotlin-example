package fp.kotlin.example.chapter11.logging

import fp.kotlin.example.chapter10.Monad
import fp.kotlin.example.chapter10.solution.FunStream
import fp.kotlin.example.chapter10.solution.flatMap
import fp.kotlin.example.chapter10.solution.fmap
import fp.kotlin.example.chapter10.solution.funStreamOf
import fp.kotlin.example.chapter10.solution.mappend
import fp.kotlin.example.chapter10.solution.mempty
import fp.kotlin.example.chapter10.solution.printFunStream

fun main(args: Array<String>) {
    val result = functionalSolution4(funStreamOf(1, 2, 3))

    printFunStream(result.fmap { it.value })    // [false, false, true]
    printFunStream(
        result.flatMap { it.logs } as FunStream<*>)  // [1 + 5, 6 * 6, 36 > 50, 2 + 5, 7 * 7, 49 > 50, 3 + 5, 8 * 8, 64 > 50]
}

private fun functionalSolution4(list: FunStream<Int>) = list
    .fmap { addFive(it) withLog "$it + 5" }
    .fmap { it.flatMap { x -> square(x) withLog "$x * $x" } }
    .fmap { it.flatMap { x -> isGreaterThan50(x) withLog "$x > 50" } }

private infix fun <T> T.withLog(log: String): WriterMonad<T> = WriterMonad(this, funStreamOf(log))

data class WriterMonad<out T>(val value: T, val logs: FunStream<String>) : Monad<T> {

    override fun <V> pure(value: V): WriterMonad<V> = WriterMonad(value, mempty())

    override fun <R> flatMap(f: (T) -> Monad<R>): WriterMonad<R> {
        val applied = f(this.value) as WriterMonad<R>
        return WriterMonad(applied.value, this.logs mappend applied.logs)
    }
}

private fun addFive(it: Int) = it + 5

private fun square(it: Int) = it * it

private fun isGreaterThan50(it: Int) = it > 50
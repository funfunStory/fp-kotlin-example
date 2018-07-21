package fp.kotlin.example.chapter11

import fp.kotlin.example.chapter05.FunList
import fp.kotlin.example.chapter05.append
import fp.kotlin.example.chapter05.forEach
import fp.kotlin.example.chapter05.funListOf
import fp.kotlin.example.chapter07.Functor

fun main(args: Array<String>) {

    val reuslt = addFive(1)
        .flatMap { exponentiation(it) }
        .flatMap { intToString(it) }

    reuslt.log
        .forEach { println(it) }

    // addFive 1
    // exponentiation 6
    // intToString 36

    println(reuslt.get())       //36

}

typealias LOG = FunList<String>

interface Monoid<A> {
    fun append(a1: A, a2: A): A
    fun empty(): A
}

data class Logger<T>(val log: LOG, private val value: T) : Monoid<LOG>, Functor<T> {

    override fun <B> fmap(transform: (T) -> B): Functor<B> = Logger(log, transform(value))

    override fun append(a1: LOG, a2: LOG): LOG = a1.append(a2)

    override fun empty() = FunList.Nil

    fun <R> flatMap(f: (T) -> Logger<R>): Logger<R> {
        val x = f(value)
        return Logger(append(log, x.log), x.value)
    }

    fun unital() = Logger(empty(), value)

    fun get(): T = value
}

fun <T> loggerOf(log: String, value: T) = Logger(funListOf(log), value)

fun addFive(n: Int) = loggerOf("addFive $n", n + 5)
fun exponentiation(n: Int) = loggerOf("exponentiation $n", when (n) {
    0 -> 1
    else -> n * n
})

fun intToString(n: Int) = loggerOf("intToString $n", n.toString())
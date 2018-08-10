package fp.kotlin.example.chapter11

import fp.kotlin.example.chapter05.*
import fp.kotlin.example.chapter07.Functor

typealias LOGS = FunList<String>
typealias LOG = String

fun main(args: Array<String>) {

    funListOf(1, 2, 3)
        .map {
            println("addFive $it")
            addFive(it)
        }
        .map {
            println("exponentiation $it")
            exponentiation(it)
        }
        .map { over50(it) }

//    addFive 1
//    addFive 2
//    addFive 3
//    exponentiation 6
//    exponentiation 7
//    exponentiation 8

    funListOf(1, 2, 3)
        .map { addFive(it) withLog "addFive $it" }
        .map { exponentiation(it) withLog "exponentiation $it" }
        .map { over50(it) }

//    addFive 1
//    addFive 2
//    addFive 3
//    exponentiation 6
//    exponentiation 7
//    exponentiation 8

    val result = funListOf(1, 2, 3)
        .map { addFive(it) loggerOf "addFive $it" }
        .map { it.flatMap { exponentiation(it) loggerOf "exponentiation $it" } }
        .map { it.fmap { over50(it) } }

    result.forEach {
        println("---LOG---")
        it.getLog().forEach { println(it) }
        println("---RESULT---")
        println(it.get())
    }

//    ---LOG---
//    addFive 1
//    exponentiation 6
//    ---RESULT---
//    false

//    ---LOG---
//    addFive 2
//    exponentiation 7
//    ---RESULT---
//    false

//    ---LOG---
//    addFive 3
//    exponentiation 8
//    ---RESULT---
//    true

    val logger = 5 loggerOf "init 5"

    val result2 = logger
        .flatMap { addFive(it) loggerOf "addFive $it" }
        .flatMap { exponentiation(it) loggerOf "exponentiation $it" }
        .fmap { over50(it) }

    println(result2.get())  //true

    result2.getLog().forEach { println(it) }
    // init 5
    // addFive 5
    // exponentiation 10


}

fun addFive(n: Int) = n + 5
fun exponentiation(n: Int) = when (n) {
    0 -> 1
    else -> n * n
}

fun over50(n: Int): Boolean = n > 50

infix fun <T> T.withLog(log: LOG): T {
    println(log)
    return this
}

infix fun <T> T.loggerOf(log: LOG): Logger<T> = Logger(funListOf(log), this)

interface Monoid<A> {
    fun append(a1: A, a2: A): A
    fun empty(): A
}

data class Logger<out T>(private val log: LOGS, private val value: T) : Functor<T>, Monoid<LOGS> {

    override infix fun <B> fmap(transform: (T) -> B): Logger<B> = Logger(log, transform(value))

    override fun append(a1: LOGS, a2: LOGS): LOGS = a1 concat (a2)

    override fun empty(): LOGS = FunList.Nil

    infix fun <R> flatMap(f: (T) -> Logger<R>): Logger<R> {
        val x = f(value)
        return Logger(append(log, x.log), x.value)
    }

    fun get() = value

    fun getLog() = log
}

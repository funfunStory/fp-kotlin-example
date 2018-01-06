package fp.kotlin.example.chapter03

import java.math.BigDecimal

sealed class Bounce<A>
data class Done<A>(val result: A): Bounce<A>()
data class More<A>(val thunk: () -> Bounce<A>): Bounce<A>()

tailrec fun <A> trampoline(bounce: Bounce<A>): A {
    return when (bounce) {
        is Done -> bounce.result
        is More -> trampoline(bounce.thunk())
    }
}

fun main(args: Array<String>) {
    println(trampoline(even(999999)))   // false
    println(trampoline(odd(999999)))   // true
    println(trampoline(squareRoot(5.0)))  // 0.528685631720282
    println(trampoline(factorial(BigDecimal(100000))))
}

private fun odd(n: Int): Bounce<Boolean> {
    return when (n) {
        0 -> Done(false)
        else -> More { even(n - 1) }
    }
}

private fun even(n: Int): Bounce<Boolean> {
    return when (n) {
        0 -> Done(true)
        else -> More { odd(n - 1) }
    }
}

// 연습문제 3.17
private fun squareRoot(n: Double): Bounce<Double> {
    return when {
        1 > n -> Done(n)
        else -> More { divideTwo(Math.sqrt(n)) }
    }
}

// 연습문제 3.17
private fun divideTwo(n: Double): Bounce<Double> {
    return when {
        1 > n -> Done(n)
        else -> More { squareRoot(n / 2) }
    }
}

// 연습문제 3.18
private fun factorial(n: BigDecimal, acc: BigDecimal = BigDecimal(1)): Bounce<BigDecimal> {
    return when (n) {
        BigDecimal(0) -> Done(acc)
        else -> More { factorial(n.dec(), n * acc) }
    }
}
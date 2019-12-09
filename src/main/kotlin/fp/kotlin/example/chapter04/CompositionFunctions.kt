package fp.kotlin.example.chapter04

import fp.kotlin.example.chapter04.solution.curried
import kotlin.math.abs

fun main() {
    println(composed(3))    // 9

    val addThree = { i: Int -> i + 3 }
    val twice = { i: Int -> i * 2 }
    val composedFunc = addThree compose twice
    println(composedFunc(3)) // 9

    val absolute = { i: List<Int> -> i.map { abs(it) } }
    val negative = { i: List<Int> -> i.map { -it } }
    val minimum = { i: List<Int> -> i.min() }

    val result1 = minimum(negative(absolute(listOf(3, -1, 5, -2, -4, 8, 14))))
    println(result1)    // -14

    val composed = minimum compose negative compose absolute
    val result2 = composed(listOf(3, -1, 5, -2, -4, 8, 14))
    println(result2)    // -14

    val powerOfTwo = { x: Int -> power(x.toDouble(), 2).toInt() }
    val gcdPowerOfTwo = { x1: Int, x2: Int -> gcd(powerOfTwo(x1), powerOfTwo(x2)) }

    println(gcdPowerOfTwo(25, 5))   // 25

    val curriedGcd1 = ::gcd.curried()
    // 잘못된 합성의 예
    val composedGcdPowerOfTwo1 = curriedGcd1 compose powerOfTwo

    println(composedGcdPowerOfTwo1(25)(5))   // 5

    val curriedGcd2 = { m: Int, n: Int -> gcd(m, powerOfTwo(n)) }.curried()
    // 적절한 합성의 예
    val composedGcdPowerOfTwo2 = curriedGcd2 compose powerOfTwo

    println(composedGcdPowerOfTwo2(25)(5))   // 25
}

private fun composed(i: Int) = addThree(twice(i))

private fun addThree(i: Int) = i + 3

private fun twice(i: Int) = i * 2

private tailrec fun gcd(m: Int, n: Int): Int = when (n) {
    0 -> m
    else -> gcd(n, m % n)
}

private tailrec fun power(x: Double, n: Int, acc: Double = 1.0): Double = when (n) {
    0 -> acc
    else -> power(x, n - 1, x * acc)
}


infix fun <F, G, R> ((F) -> R).compose(g: (G) -> F): (G) -> R {
    return { gInput: G -> this(g(gInput)) }
}
package fp.kotlin.example.chapter03.solution

import fp.kotlin.example.chapter03.Bounce
import fp.kotlin.example.chapter03.Done
import fp.kotlin.example.chapter03.More
import fp.kotlin.example.chapter03.trampoline

/**
 * 연습문제 3-18
 *
 * 연습문제 3-17에서 작성한 함수를 ``trampoline`` 함수를 사용하여 재작성해보자.
 */

fun main(args: Array<String>) {
    println(trampoline(squareRoot(5.0)))  // 0.528685631720282
}

private fun squareRoot(n: Double): Bounce<Double> = when {
    n < 1 -> Done(n)
    else -> More { divideTwo(Math.sqrt(n)) }
}

private fun divideTwo(n: Double): Bounce<Double> = when {
    n < 1 -> Done(n)
    else -> More { squareRoot(n / 2) }
}
package fp.kotlin.example.chapter03.exercise

import fp.kotlin.example.chapter03.Bounce
import fp.kotlin.example.chapter03.trampoline

/**
 * 연습문제 3-18
 *
 * 연습문제 3-17에서 작성한 함수를 ``trampoline`` 함수를 사용하여 재작성해보자.
 */

fun main() {
    require(0.528685631720282 == trampoline(squareRoot(5.0)))
}

private fun squareRoot(n: Double): Bounce<Double> = TODO()

private fun divideTwo(n: Double): Bounce<Double> = TODO()
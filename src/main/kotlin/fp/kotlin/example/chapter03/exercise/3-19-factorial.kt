package fp.kotlin.example.chapter03.exercise

import fp.kotlin.example.chapter03.Bounce
import fp.kotlin.example.chapter03.trampoline
import java.math.BigDecimal

/**
 * 연습문제 3-19
 *
 * 연습문제 3-12에서 작성한 ``factorial`` 함수를 ``trampoline`` 함수를 사용하여 재작성하고, 100000! 값을 구해보자.
 *
 * 힌트: java.math.BigDecimal을 사용하라.
 */

fun main(args: Array<String>) {
    require(BigDecimal(3628800) == trampoline(factorial(BigDecimal(10))))
    require(BigDecimal(Long.MAX_VALUE) < trampoline(factorial(BigDecimal(100000))))
}

private fun factorial(n: BigDecimal, acc: BigDecimal = BigDecimal(1)): Bounce<BigDecimal> = TODO()
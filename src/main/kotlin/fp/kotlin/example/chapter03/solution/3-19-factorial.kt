package fp.kotlin.example.chapter03.solution

import fp.kotlin.example.chapter03.Bounce
import fp.kotlin.example.chapter03.Done
import fp.kotlin.example.chapter03.More
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
    println(trampoline(factorial(BigDecimal(10))))  // 3628800
    println(trampoline(factorial(BigDecimal(100000))))  // Very big number
}

private fun factorial(n: BigDecimal, acc: BigDecimal = BigDecimal(1)): Bounce<BigDecimal> = when (n) {
    BigDecimal(0) -> Done(acc)
    else -> More { factorial(n.dec(), n * acc) }
}
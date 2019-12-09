package fp.kotlin.example.chapter11.solution

import fp.kotlin.example.chapter11.logging.WriterMonad

/**
 *
 * 연습문제 11-1
 *
 * WriterMonad가 모나드의 법칙을 만족하는지 확인해보자.
 *
 */

fun main() {
    val f: (Int) -> WriterMonad<Int> = { x: Int -> WriterMonad.pure(x) }
    val value = 3

    require(WriterMonad.pure(value) flatMap f == f(value))

    val m = WriterMonad.pure(value)
    require(m flatMap { WriterMonad.pure(it) } == m)

    val g = { _: Int -> WriterMonad.pure(value) }
    require((m flatMap f) flatMap g == m flatMap { x :Int -> f(x) flatMap g })
}
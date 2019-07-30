package fp.kotlin.example.chapter08.exercise

import fp.kotlin.example.chapter08.Just
import fp.kotlin.example.chapter08.Maybe
import fp.kotlin.example.chapter08.apply
import fp.kotlin.example.chapter08.pure

/**
 *
 * 연습문제 8-15
 *
 * ``listA3`` 함수를 구현해보자. 이 함수는 삼항 함수를 받아서 세개의 애플리케이티브 펑터를 적용하는 승격 함수다.
 *
 */
fun main() {

    val lifted = liftA3 { x: Int, y: Int, z: Int -> x + y + z }
    require(lifted(Just(1), Just(2), Just(3)) == Just(6))

    val lifted2 = liftA3 { x: String, y: String, z: String -> x + y + z }
    require(lifted2(Just("Hello, "), Just("Kotlin, "), Just("FP")) == Just("Hello, Kotlin, FP"))

    val lifted3 = liftA3 { x: Int, y: String, z: String -> x.toString() + y + z }
    require(lifted3(Just(10), Just("Hello, "), Just("Kotlin")) == Just("10Hello, Kotlin"))
}

private fun <P1, P2, P3, R> ((P1, P2, P3) -> R).curried(): (P1) -> (P2) -> (P3) -> R = { p1: P1 ->
    { p2: P2 -> { p3: P3 -> this(p1, p2, p3) } }
}

private fun <A, B, C, R> liftA3(
    tripleFunction: (A, B, C) -> R): (Maybe<A>, Maybe<B>, Maybe<C>) -> Maybe<R> = TODO()
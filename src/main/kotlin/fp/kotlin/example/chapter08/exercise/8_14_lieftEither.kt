package fp.kotlin.example.chapter08.exercise

import fp.kotlin.example.chapter04.solution.curried
import fp.kotlin.example.chapter08.Either
import fp.kotlin.example.chapter08.Right
import fp.kotlin.example.chapter08.apply
import fp.kotlin.example.chapter08.pure

/**
 *
 * 연습문제 8-14
 *
 * Either 에도 동작하는 ``liftA2`` 함수를 추가해보자.
 *
 */
fun main(args: Array<String>) {

    val lifted = liftA2 { x: Int, y: Int -> x + y }
    require(lifted(Right(1), Right(2)) == Right(3))

    val lifted2 = liftA2 { x: String, y: String -> x + y }
    require(lifted2(Right("Hello, "), Right("Kotlin")) == Right("Hello, Kotlin"))

    val lifted3 = liftA2 { x: Int, y: String -> x.toString() + y }
    require(lifted3(Right(10), Right("Kotlin")) == Right("10Kotlin"))
}

private fun <A, B, R> liftA2(binaryFunction: (A, B) -> R): (Either<Nothing, A>, Either<Nothing, B>) -> Either<Nothing, R> = TODO()
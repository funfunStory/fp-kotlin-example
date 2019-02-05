package fp.kotlin.example.chapter08.exercise

import fp.kotlin.example.chapter08.Either
import fp.kotlin.example.chapter08.Left
import fp.kotlin.example.chapter08.Right

/**
 *
 * 연습문제 8-17
 *
 * Either 에도 동작하는 ``sequenceA`` 함수를 추가하고 테스트 해보자.
 *
 */
fun main(args: Array<String>) {

    val eitherList: Cons<Right<Int>> = Cons(Right(1), Cons(Right(2), Cons(Right(3), Nil)))
    require(sequenceAByFoldRight(eitherList) == Right(Cons(1, Cons(2, Cons(3, Nil)))))

    val eitherList2: Cons<Either<String, Int>> = Cons(Right(1), Cons(Left("test"), Cons(Right(3), Nil)))
    require(sequenceAByFoldRight(eitherList2) == Left("test"))

}

private fun <T> cons() = { x: T, xs: FunList<T> -> Cons(x, xs) }

private fun <L, R> sequenceAByFoldRight(eitherList: FunList<Either<L, R>>): Either<L, FunList<R>> = TODO()

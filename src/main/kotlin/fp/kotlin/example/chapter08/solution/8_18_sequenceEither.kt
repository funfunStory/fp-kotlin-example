package fp.kotlin.example.chapter08.solution

import fp.kotlin.example.chapter04.solution.curried
import fp.kotlin.example.chapter08.*

/**
 *
 * 연습문제 8-18
 *
 * Either 에도 동작하는 ``sequenceA`` 함수를 추가하고 테스트 해보자.
 *
 */
fun main() {

    val eitherList: Cons<Right<Int>> = Cons(Right(1), Cons(Right(2), Cons(Right(3), Nil)))
    require(sequenceAByFoldRight(eitherList) == Right(Cons(1, Cons(2, Cons(3, Nil)))))

    val eitherList2: Cons<Either<String, Int>> = Cons(Right(1), Cons(Left("test"), Cons(Right(3), Nil)))
    require(sequenceAByFoldRight(eitherList2) == Left("test"))

}

private fun <T> cons() = { x: T, xs: FunList<T> -> Cons(x, xs) }

private fun <L, R> sequenceAByFoldRight(
    eitherList: FunList<Either<L, R>>): Either<L, FunList<R>> =
    when (eitherList) {
        Nil -> Right(Nil)
        is Cons -> Either.pure(
            cons<R>().curried()) apply eitherList.head apply sequenceAByFoldRight(
            eitherList.tail)
    }

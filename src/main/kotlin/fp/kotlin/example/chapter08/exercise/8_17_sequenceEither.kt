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

    val eitherList: ACons<Right<Int>> = ACons(Right(1), ACons(Right(2), ACons(Right(3), ANil)))
    require(sequenceAByFoldRight(eitherList) == Right(ACons(1, ACons(2, ACons(3, ANil)))))

    val eitherList2: ACons<Either<String, Int>> = ACons(Right(1), ACons(Left("test"), ACons(Right(3), ANil)))
    require(sequenceAByFoldRight(eitherList2) == Left("test"))

}

private fun <T> cons() = { x: T, xs: AFunList<T> -> ACons(x, xs) }

private fun <L, R> sequenceAByFoldRight(eitherList: AFunList<Either<L, R>>): Either<L, AFunList<R>> = TODO()

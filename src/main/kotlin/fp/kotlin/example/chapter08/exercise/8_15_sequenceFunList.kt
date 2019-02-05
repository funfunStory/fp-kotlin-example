package fp.kotlin.example.chapter08.exercise

import fp.kotlin.example.chapter04.solution.curried

/**
 *
 * 연습문제 8-15
 *
 * FunList 에도 동작하는 ``sequenceA`` 함수를 추가하고 테스트 해보자.
 *
 */
fun main(args: Array<String>) {

    val listOfList = Cons(Cons(1, Cons(2, Cons(3, Nil))), Nil)
    require(sequenceAByFoldRight(listOfList) ==
        Cons(
            Cons(1, Nil),
            Cons(
                Cons(2, Nil),
                Cons(
                    Cons(3, Nil),
                    Nil)
            )
        )
    )
}

private fun <T> cons() = { x: T, xs: FunList<T> -> Cons(x, xs) }

private fun <T> sequenceAByFoldRight(listOfList: FunList<FunList<T>>): FunList<FunList<T>> = TODO()
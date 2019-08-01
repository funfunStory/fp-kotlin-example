package fp.kotlin.example.chapter08.solution

import fp.kotlin.example.chapter04.solution.curried

/**
 *
 * 연습문제 8-12
 *
 * FunList 에도 동작하는 ``liftA2`` 함수를 추가해보자.
 *
 */
fun main() {

    val lifted = liftA2 { x: Int, y: Int -> x + y }
    require(lifted(Cons(1, Nil), Cons(2, Nil)) == Cons(3, Nil))

    val lifted2 = liftA2 { x: String, y: String -> x + y }
    require(lifted2(Cons("Hello, ", Nil), Cons("Kotlin", Nil)) == Cons("Hello, Kotlin", Nil))

    val lifted3 = liftA2 { x: Int, y: String -> x.toString() + y }
    require(lifted3(Cons(10, Nil), Cons("Kotlin", Nil)) == Cons("10Kotlin", Nil))

    require(lifted3(Cons(10, Cons(20, Nil)), Cons("Hello, ", Cons("Kotlin", Nil))) ==
        Cons("10Hello, ", Cons("10Kotlin", Cons("20Hello, ", Cons("20Kotlin", Nil)))))

}

private fun <A, B, R> liftA2(binaryFunction: (A, B) -> R): (FunList<A>, FunList<B>)-> FunList<R>  = { f1: FunList<A>, f2: FunList<B> ->
    FunList.pure(binaryFunction.curried()) apply f1 apply f2
}
package fp.kotlin.example.chapter10.solution

import fp.kotlin.example.chapter10.Monad

/**
 *
 * 연습문제 10-7
 *
 * 함수 합성 관점에서 리스트 모나드가 세가지 법칙을 만족하는지 확인하라.
 *
 */

fun main() {

    val f = {x : Int -> FunList.Cons(x * 10, FunList.Nil) }
    val g = {x : Int -> FunList.Cons(x + 1, FunList.Nil) }
    val h = {x : Int -> FunList.Cons(x - 5, FunList.Nil) }
    val pure = { x : Int -> FunList.pure(x) }

    require((pure compose f)(10) == f(10))
    require((f compose pure)(10) == f(10))
    require(((f compose g) compose h)(10) == (f compose (g compose h))(10))
}

infix fun <F, G, R> ((F) -> Monad<R>).compose(g: (G) -> Monad<F>): (G) -> Monad<R> {
    return { gInput: G -> g(gInput) flatMap this }
}
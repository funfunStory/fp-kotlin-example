package fp.kotlin.example.chapter10.solution

/**
 *
 * 연습문제 10-4
 *
 * 리스트 모나드가 왼쪽 항등 법칙을 만족하는지 확인해보자.
 *
 */

fun main() {

    val f :(Int) -> FunList<Int> = { x: Int -> FunList.Cons(x * 3, FunList.Nil) }
    val value = 3

    require(FunList.pure(value) flatMap f == f(value))
}
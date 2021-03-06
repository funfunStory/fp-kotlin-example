package fp.kotlin.example.chapter10.solution

/**
 *
 * 연습문제 10-5
 *
 * 리스트 모나드가 오른쪽 항등 법칙을 만족하는지 확인해보자.
 *
 */

fun main() {

    val m = FunList.Cons(3, FunList.Nil)

    require(m flatMap { FunList.pure(it) } == m)
}
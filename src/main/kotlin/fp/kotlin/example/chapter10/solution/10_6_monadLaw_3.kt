package fp.kotlin.example.chapter10.solution

/**
 *
 * 연습문제 10-6
 *
 * 리스트 모나드가 결합 법칙을 만족하는지 확인해보자.
 *
 */

fun main() {

    val f = {x : Int -> FunList.Cons(x * 10, FunList.Nil) }
    val g = {x : Int -> FunList.Cons("value : $x", FunList.Nil) }
    val m = FunList.Cons(3, FunList.Nil)

    require((m flatMap f) flatMap g == m flatMap { x :Int -> f(x) flatMap g })
}
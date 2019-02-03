package fp.kotlin.example.chapter10.exercise

/**
 *
 * 연습문제 10-6
 *
 * 리스트 모나드가 결합 법칙을 만족하는지 확인해보자.
 *
 */

fun main() {

    val f = {x : Int -> Cons(x * 10, Nil) }
    val g = {x : Int -> Cons("value : $x", Nil) }
    val m = Cons(3, Nil)

    require((m flatMap f) flatMap g == m flatMap { x :Int -> f(x) flatMap g })
}
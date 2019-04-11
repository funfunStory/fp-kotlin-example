package fp.kotlin.example.chapter08.solution

/**
 *
 * 연습문제 8-9
 *
 * 연습문제 8-3 에서 만든 리스트 애플리케이티브 펑터가 준동형 사상 법칙을 만족하는지 확인해보자.
 *
 */

fun main(args: Array<String>) {

    val function: (Int) -> Int = { x: Int -> x * 10 }
    val value: FunList<Int> = Cons(10, Cons(20, Nil))

    val left = FunList.pure(function) apply value
    val right = value.fmap(function)

    require(left == right)
}
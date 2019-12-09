package fp.kotlin.example.chapter08.solution

/**
 *
 * 연습문제 8-9
 *
 * 연습문제 8-3 에서 만든 리스트 애플리케이티브 펑터가 준동형 사상 법칙을 만족하는지 확인해보자.
 *
 */

fun main() {

    val function: (Int) -> Int = { x: Int -> x * 10 }
    val value = 1

    val left = FunList.pure(function) apply FunList.pure(value)
    val right = FunList.pure(function(value))

    require(left == right)
}
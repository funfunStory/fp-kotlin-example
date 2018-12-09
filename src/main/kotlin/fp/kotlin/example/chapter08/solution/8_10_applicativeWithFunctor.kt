package fp.kotlin.example.chapter08.solution

/**
 *
 * 연습문제 8-10
 *
 * 연습문제 8-3 에서 만든 리스트 애플리케이티브 펑터가 ``pure(function) apply af = af.fmap(function)``를 만족하는지 확인해보자.
 *
 */

fun main(args: Array<String>) {

    val function: (Int) -> Int = { x: Int -> x * 10 }
    val value = 10

    val applicative = AFunList.pure(function) apply AFunList.pure(value)
    val functor = AFunList.pure(value).fmap(function)

    require(applicative == functor)
}
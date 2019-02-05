package fp.kotlin.example.chapter08.exercise

import fp.kotlin.example.chapter04.solution.curried

/**
 *
 * 연습문제 8-1
 *
 * 7장에서 만든 리스트 펑터를 사용해서 ``product`` 함수에 [1, 2, 3, 4]가 적용된 부분 적용 함수의 리스트 만들어보자.
 * 만들어진 리스트에 여러가지 값을 넣어서 테스트해보자. 예를들어 5를 넣으면 [5, 10, 15, 20]이 되어야 한다.
 *
 */

fun main(args: Array<String>) {

    val product: (Int, Int) -> Int = { x: Int, y: Int -> x * y }
    val curriedProduct: (Int) -> (Int) -> Int = product.curried()
    val list = listOf(1, 2, 3, 4)

    val productWithList: (Int) -> List<Int> = TODO()

    require(productWithList(5) == listOf(5, 10, 15, 20))
    require(productWithList(10) == listOf(10, 20, 30, 40))
}
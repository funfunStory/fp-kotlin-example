package fp.kotlin.example.chapter04.solution

/**
 * 연습문제 4-5
 *
 * 숫자(Int)의 리스트를 받아서 최대값의 제곱을 구하는 함수를 작성해보자. 이때 반드시 ``max`` 함수와 ``power`` 함수를 만들어 합성해야 한다.
 */

fun main() {
    val list = listOf(1, 2, 3, 4, 5, 6, 7)
    val list2 = listOf(10, 2, 13, 4, 0, 6, 1)

    require(power(max(list)) == 49)
    require(power(max(list2)) == 169)

}

val max: (List<Int>) -> Int = { list: List<Int> -> list.max()!! }

val power: (Int) -> Int = { value: Int -> value * value }
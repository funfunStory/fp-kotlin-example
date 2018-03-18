package fp.kotlin.example.chapter03.solution

import fp.kotlin.example.head
import fp.kotlin.example.tail

/**
 * 연습문제 3-16
 *
 * 연습문제 3-6에서 작성한 ``elem`` 함수가 꼬리 재귀인지 확인해보자. 만약 꼬리 재귀가 아니라면 개선해보자.
 */

fun main(args: Array<String>) {
    println(elem(5, listOf(1, 3, 5)))   // true
    println(elem(5, listOf(1, 3, 7)))   // false
}

private tailrec fun elem(n: Int, list: List<Int>): Boolean = when {
    list.isEmpty() -> false
    n == list.head() -> true
    else -> elem(n, list.tail())
}
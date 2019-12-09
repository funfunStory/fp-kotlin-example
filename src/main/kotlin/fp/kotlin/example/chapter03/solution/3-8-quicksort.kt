package fp.kotlin.example.chapter03.solution

import fp.kotlin.example.head
import fp.kotlin.example.tail

/**
 * 연습문제 3-8
 *
 * 퀵정렬 알고리즘의 ``quicksort`` 함수를 작성해 보자.
 *
 * 힌트1: 퀵정렬 알고리즘의 동작 방식은 아래 위키를 참고하자.
 * https://en.wikipedia.org/wiki/Quicksort
 * 힌트2: 리스트를 분할하기 위해 함수 ``partition``을 활용하자.
 */

fun main() {
    println(quicksort(listOf(5, 3, 7, 6, 2, 1, 4)))     // [1, 2, 3, 4, 5, 6, 7]
}

private fun quicksort(list: List<Int>): List<Int> = when {
    list.isEmpty() -> list
    else -> {
        val pivot = list.head()
        val (small, bigger) = list.tail().partition { it < pivot }
        quicksort(small) + listOf(pivot) + quicksort(bigger)
    }
}
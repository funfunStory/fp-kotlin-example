package fp.kotlin.example.chapter04.exercise

import fp.kotlin.example.head
import fp.kotlin.example.tail

/**
 * 연습문제 4-8
 *
 * 연습문제 4-7에서 작성한 ``takeWhile``를 수정하여, 무한대를 입력받을 수 있는 ``takeWhile``을 tailrec으로 작성해보자.
 */

fun main(args: Array<String>) {
    require(listOf(1, 2, 3, 4, 5, 6, 7, 8, 9) == takeWhile({ p -> p < 10 }, generateSequence(1) { it + 1 }))
}

private tailrec fun <P> takeWhile(predicate: (P) -> Boolean, sequence: Sequence<P>, acc: List<P> = listOf()): List<P> = TODO()
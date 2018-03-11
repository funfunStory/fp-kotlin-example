package fp.kotlin.example.chapter04.solution

import fp.kotlin.example.head
import fp.kotlin.example.tail

/**
 * 연습문제 4-7
 *
 * 연습문제 4-6에서 작성한 ``takeWhile``를 수정하여, 무한대를 입력받을 수 있는 ``takeWhile``을 tailrec으로 작성해보자.
 */

fun main(args: Array<String>) {
    println(takeWhile({ p -> 10 < p }, generateSequence(1) { it + 1 }))   // [1, 2]
}

private tailrec fun <P> takeWhile(predicate: (P) -> Boolean, sequence: Sequence<P>, acc: List<P> = listOf()): List<P> = when {
    sequence.none() || predicate(sequence.head()) -> acc
    else -> takeWhile(predicate, sequence.tail(), acc + sequence.head())
}
package fp.kotlin.example.chapter04.exercise

import fp.kotlin.example.head
import fp.kotlin.example.tail

/**
 * 연습문제 4-6
 *
 * ``takeWhile`` 함수는 조건 함수와 리스트를 입력받아서 조건 함수에 리스트의 구성값을 매개변수로 호출했을때, 결과가 true일때까지의 모든 값들의
 * 리스트를 반환한다. 예를들어, 입력 리스트가 1, 2, 3, 4, 5로 구성되어 있을때, 조건 함수가 3보다 큰 값이라면 1과 2를 구성 값으로 가지는 리스트를
 * 반환한다. ``takeWhile`` 함수를 작성해보자.
 */

fun main(args: Array<String>) {
    require(listOf(1, 2) == takeWhile({ p -> 2 < p }, listOf(1, 2, 3, 4, 5)))
    require(listOf('h', 'e', 'l', 'l', 'o') == takeWhile({ p -> ' ' == p }, "hello world".toList()))
}

private tailrec fun <P> takeWhile(predicate: (P) -> Boolean, list: List<P>, acc: List<P> = listOf()): List<P> = TODO()
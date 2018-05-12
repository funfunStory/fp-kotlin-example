package fp.kotlin.example.chapter05.exercise

import fp.kotlin.example.chapter05.FunList
import fp.kotlin.example.chapter05.FunList.Cons
import fp.kotlin.example.chapter05.FunList.Nil
import fp.kotlin.example.chapter05.funListOf

/**
 *
 * 연습문제 5-8
 *
 * 타입 T를 입력받아 Boolean을 반환하는 함수 f 를 입력받고,
 * 전체 List 중 앞에서부터 함수 f를 만족 할때까지의 List를 반환는 takeWhile 함수를 구현하자(함수 f를 만족하지 않는다면 이전 List를 반환).
 * 이때 원본 리스트가 바뀌지 않고, 새로운 리스트를 반환할때 매번 리스트를 생성하지 않아야 한다.
 *
 */

fun main(args: Array<String>) {

    val intList = Cons(1, Cons(2, Cons(3, Cons(4, Nil))))
    require(intList.takeWhile { it < 4 } == funListOf(1, 2, 3))
}

fun <T> FunList<T>.takeWhile(f: (T) -> Boolean): FunList<T> = TODO()
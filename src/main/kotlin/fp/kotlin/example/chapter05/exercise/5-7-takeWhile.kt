package fp.kotlin.example.chapter05.exercise

import fp.kotlin.example.chapter05.FunList
import fp.kotlin.example.chapter05.FunList.Cons
import fp.kotlin.example.chapter05.FunList.Nil
import fp.kotlin.example.chapter05.funListOf

/**
 *
 * 연습문제 5-7
 *
 * 어떤 타입 T를 입력받아 Boolean을 반환하는 함수 p 를 받아서 리스트의 앞에서부터 함수 p를 만족하는 값까지의 값들의 리스트를 반환는 takeWhile 함수를
 * 구현하자(모든 값이 함수 p를 만족하지 않는다면 원본 List를 반환). 이때 원본 리스트가 바뀌지 않고, 새로운 리스트를 반환할때 매번 리스트를 생성하지
 * 않아야 한다.
 *
 */

fun main(args: Array<String>) {

    val intList = Cons(1, Cons(2, Cons(3, Cons(4, Nil))))
    require(intList.takeWhile { it < 4 } == funListOf(1, 2, 3))
    require(intList.takeWhile { it < 2 } == funListOf(1))
    require(intList.takeWhile { it < 0 } == Nil)
}

tailrec fun <T> FunList<T>.takeWhile(acc: FunList<T> = Nil, p: (T) -> Boolean): FunList<T> = TODO()
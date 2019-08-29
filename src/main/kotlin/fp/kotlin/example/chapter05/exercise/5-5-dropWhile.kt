package fp.kotlin.example.chapter05.exercise

import fp.kotlin.example.chapter05.FunList
import fp.kotlin.example.chapter05.FunList.Cons
import fp.kotlin.example.chapter05.FunList.Nil
import fp.kotlin.example.chapter05.funListOf

/**
 * 연습문제 5-5
 *
 * 다음과 같이 동작하는 ``dropWhile`` 함수를 구현하자. 타입 ``T``를 입력받아 ``Boolean``을 반환하는 함수 ``p``를 입력받는다. 리스트의 앞에서부터
 * 함수 ``p``를 만족하기 전까지 ``drop``을 하고, 나머지 값들의 리스트를 반환한다. 이때 원본 리스트가 바뀌지 않고, 새로운 리스트를 반환할 때 매번 리스트를 생성하지 않아야 한다.
 *
 * 힌트: 함수의 선언 타입은 아래와 같다.
 */
fun main() {

    val intList = Cons(1, Cons(2, Cons(3, Cons(4, Nil))))
    require(intList.dropWhile { it % 2 == 0 } == funListOf(2, 3, 4))
    require(intList.dropWhile { it > 3 } == funListOf(4))
    require(intList.dropWhile { it > 5 } == Nil)
}

tailrec fun <T> FunList<T>.dropWhile(p: (T) -> Boolean): FunList<T> = TODO()
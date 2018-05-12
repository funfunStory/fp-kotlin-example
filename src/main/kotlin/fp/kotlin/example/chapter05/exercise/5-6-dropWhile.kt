package fp.kotlin.example.chapter05.exercise

import fp.kotlin.example.chapter05.FunList
import fp.kotlin.example.chapter05.FunList.Cons
import fp.kotlin.example.chapter05.FunList.Nil
import fp.kotlin.example.chapter05.funListOf

/**
 *
 * 연습문제 5-6
 *
 * 전체 List 중 앞에서부터 n개를 제외한 List를 반환는 drop 함수를 구현하자.
 * 이때 원본 리스트가 바뀌지 않고, 새로운 리스트를 반환할때 매번 리스트를 생성하지 않아야 한다.
 *
 * 힌트: 함수의 선언 타입은 아래와 같다.
 *
 */

fun main(args: Array<String>) {

    val intList = Cons(1, Cons(2, Cons(3, Cons(4, Nil))))
    require(intList.dropWhile { it % 2 == 0 } == funListOf(2, 3, 4))
}

tailrec fun <T> FunList<T>.dropWhile(f: (T) -> Boolean): FunList<T> = TODO()
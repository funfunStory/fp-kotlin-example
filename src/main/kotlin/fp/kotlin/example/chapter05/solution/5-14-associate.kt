package fp.kotlin.example.chapter05.solution

import fp.kotlin.example.chapter05.FunList
import fp.kotlin.example.chapter05.foldRight
import fp.kotlin.example.chapter05.funListOf

/**
 *
 * 연습문제 5-14
 *
 * zip 함수는 리스트와 리스트를 조합해서 리스트를 생성하는 함수이다.
 * 여기서는 리스트의 값을 입력받은 조합 함수에 의해서 연관 자료구조인 맵을 생성하는 associate 함수를 작성해보자.
 *
 * 힌트: 함수의 선언 타입은 아래와 같다.
 *
 */

fun main(args: Array<String>) {
    require(
        funListOf(1, 2, 3, 4, 5).associate { it to it * 10 } == mapOf(1 to 10, 2 to 20, 3 to 30, 4 to 40, 5 to 50))
}

fun <T, R> FunList<T>.associate(f: (T) -> Pair<T, R>): Map<T, R> = foldRight(mapOf()) { value, acc -> acc.plus(f(value)) }


package fp.kotlin.example.chapter05.exercise

import fp.kotlin.example.chapter05.FunList
import fp.kotlin.example.chapter05.funListOf

/**
 *
 * 연습문제 5-17
 *
 * FunList의 값들을 입력받은 키 생성 함수를 기준으로 맵을 생성하는 groupBy 함수를 작성해보자.
 *
 * 힌트: 함수의 선언 타입은 아래와 같다
 *
 */

fun main(args: Array<String>) {
    require(
        funListOf(1, 2, 3).groupBy { it } == mapOf(1 to funListOf(1), 2 to funListOf(2), 3 to funListOf(3)))
    require(funListOf(1, 2, 3, 4, 5, 6).groupBy { it % 2 == 0 } ==
        mapOf(false to funListOf(1, 3, 5), true to funListOf(2, 4, 6)))
}

fun <T, K> FunList<T>.groupBy(f: (T) -> K): Map<K, FunList<T>> = TODO()
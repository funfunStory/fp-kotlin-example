package fp.kotlin.example.chapter05.solution

import fp.kotlin.example.chapter05.foldLeft
import fp.kotlin.example.chapter05.toFunList

/**
 *
 * 연습문제 5-8
 *
 * Factorial 함수를 FunList의 foldLeft를 사용하여 재작성해보자.
 *
 * 힌트: (1..n).toFunList를 사용해서 List를 만들자.
 *      함수의 선언 타입은 아래와 같다.
 *
 */

fun main(args: Array<String>) {

    val result = factorialByFoldLeft(5)
    require(5 * 4 * 3 * 2 * 1 == result)

}

fun factorialByFoldLeft(n: Int): Int {
    val list = (1..n).toFunList()
    return list.foldLeft(1) { x, acc -> x * acc }
}
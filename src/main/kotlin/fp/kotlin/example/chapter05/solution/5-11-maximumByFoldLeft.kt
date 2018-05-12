package fp.kotlin.example.chapter05.solution

import fp.kotlin.example.chapter05.FunList
import fp.kotlin.example.chapter05.foldLeft
import fp.kotlin.example.chapter05.funListOf

/**
 *
 * 연습문제 5-11
 *
 * maximum 함수를 foldLeft 함수를 사용해서 재작성 해보자.
 *
 * 힌트: 함수의 선언 타입은 아래와 같다.
 */

fun main(args: Array<String>) {
    val list = funListOf(1, 2, 3, 4, 5)
    require(list.maximumByFoldLeft() == 5)
}

fun FunList<Int>.maximumByFoldLeft(): Int = foldLeft(0) { acc, elm ->
    if (acc > elm) {
        acc
    } else {
        elm
    }
}

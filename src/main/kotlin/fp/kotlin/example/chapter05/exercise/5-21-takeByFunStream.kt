package fp.kotlin.example.chapter05.exercise

import fp.kotlin.example.chapter05.FunStream
import fp.kotlin.example.chapter05.funStreamOf
import fp.kotlin.example.chapter05.generateFunStream
import fp.kotlin.example.chapter05.getHead
import fp.kotlin.example.chapter05.toFunStream

/**
 *
 * 연습문제 5-21
 *
 * 무한대에 담은 값에서 필요한 값을 가져오기 위해서 필요한 take 함수를 추가하자.
 * Take 함수를 사용하여 무한 컬렉션에서 5개만 가져와서 합계를 구해보자.
 *
 * 힌트: 함수의 선언 타입은 아래와 같다.
 *
 */

fun main(args: Array<String>) {
    require((1..100000000)
        .toFunStream()
        .take(1).getHead() == 1)
    require(generateFunStream(0) { it + 5 }.take(5) == funStreamOf(0, 5, 10, 15, 20))
}

fun <T> FunStream<T>.take(n: Int): FunStream<T> = TODO()
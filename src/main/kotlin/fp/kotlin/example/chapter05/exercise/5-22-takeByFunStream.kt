package fp.kotlin.example.chapter05.exercise

import fp.kotlin.example.chapter05.*

/**
 * 연습문제 5-22
 *
 * ``FunStream``에서 필요한 값을 가져오는 ``take`` 함수를 추가하자. ``FunStream``은 무한대를 표현한 컬렉션이다. ``take`` 함수를 사용하여 값을
 * 5개 가져온 후 합계를 구해 보자.
 *
 * 힌트: 함수의 선언 타입은 아래와 같다.
 */
fun main() {
    require((1..100000000)
        .toFunStream()
        .take(1)
        .getHead() == 1)
    require(generateFunStream(0) { it + 5 }
        .take(5) == funStreamOf(0, 5, 10, 15, 20))
}

fun <T> FunStream<T>.take(n: Int): FunStream<T> = TODO()
package fp.kotlin.example.chapter09.solution

import fp.kotlin.example.chapter05.funListOf
import fp.kotlin.example.chapter09.mconcat


/**
 *
 * 연습문제 9-5
 *
 * Any 모노이드의 ``mconcat`` 함수를 테스트 해보자. 예를들어 [true, true, true], [false, false, false], [ture, false, true] 인 경우
 *
 */


fun main(args: Array<String>) {
    val x = funListOf(true, true, true)
    val y = funListOf(false, false, false)
    val z = funListOf(true, false, true)

    AnyMonoid().run {
        require(mconcat(x) == true)
        require(mconcat(y) == false)
        require(mconcat(z) == true)
    }
}
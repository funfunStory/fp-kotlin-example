package fp.kotlin.example.chapter09.solution

import fp.kotlin.example.chapter05.funListOf
import fp.kotlin.example.chapter09.mconcat

/**
 *
 * 연습문제 9-6
 *
 * ``All`` 모노이드의 ``mconcat`` 함수를 테스트 해 보자. 예를 들어 입력이 [true, true, true], [false, false, false],
 * [ture, false, true] 인 경우 결과는 어떤가?
 */
fun main(args: Array<String>) {
    val x = funListOf(true, true, true)
    val y = funListOf(false, false, false)
    val z = funListOf(true, false, true)

    AllMonoid().run {
        require(mconcat(x) == true)
        require(mconcat(y) == false)
        require(mconcat(z) == false)
    }
}
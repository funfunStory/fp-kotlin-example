package fp.kotlin.example.chapter10.exercise

import fp.kotlin.example.chapter10.Just
import fp.kotlin.example.chapter10.Maybe
import fp.kotlin.example.chapter10.Nothing

/**
 * 연습문제 10-3
 *
 * 아래와 같이 중첩된 클래스 구조 중간에 nullable하지 않은 프로퍼티 ``D3``가 있다고 가정했을때, 본문 예제와 동일한 기능의 ``getValueOfD4`` 함수를 작성하고 테스트해보자.
 *
 */

fun main() {

    val a1 = A4(Just(B4(Just(C4(D4(Just("FP")))))))
    require(Just("FP") == getValueOfD4(a1))
    require(Just("FP") == getValueOfD4_2(a1))

    val a2 = A4(Nothing)
    require(Nothing == getValueOfD4(a2))
    require(Nothing == getValueOfD4_2(a2))

    val a3 = A4(Just(B4(Nothing)))
    require(Nothing == getValueOfD4(a3))
    require(Nothing == getValueOfD4_2(a3))

    val a4 = A4(Just(B4(Just(C4(D4(Just("FP")))))))
    require(Just("FP") == getValueOfD4(a4))
    require(Just("FP") == getValueOfD4_2(a4))

    val a5 = A4(Just(B4(Just(C4(D4(Nothing))))))
    require(Nothing == getValueOfD4(a5))
    require(Nothing == getValueOfD4_2(a5))
}

class A4(val b: Maybe<B4>)
class B4(val c: Maybe<C4>)
class C4(val d: D4)
class D4(val value: Maybe<String>)

fun getValueOfD4(a: A4): Maybe<String> = TODO()

fun getValueOfD4_2(a: A4): Maybe<String> = TODO()
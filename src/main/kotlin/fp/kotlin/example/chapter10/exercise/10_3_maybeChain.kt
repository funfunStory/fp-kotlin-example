package fp.kotlin.example.chapter10.exercise

import fp.kotlin.example.chapter10.Just
import fp.kotlin.example.chapter10.Maybe
import fp.kotlin.example.chapter10.Nothing

/**
 * 연습문제 10-3
 *
 * 다음과 같이 중첩된 클래스 중간에 널이 될 수 없는 프로퍼티 ``D4``가 있다고 가정해 보자. 이때 ``A4``에서 ``D4``의 값 ``value``를 얻기 위한  함수
 * ``getValueOfD4``를 작성하고 테스트해 보자.
 *
 * 힌트 : d에 접근할 때  fmap을 사용해 값에 접근 하고, 함수 체이닝을 이어 갈 수 있도록 getValueOfD4_2 함수도 작성하자.
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
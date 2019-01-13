package fp.kotlin.example.chapter09.solution

import fp.kotlin.example.chapter09.Monoid

/**
 *
 * 연습문제 9-1
 *
 * ``||`` 연산을 Any 모노이드로 만들어보자.
 *
 */

fun main(args: Array<String>) {
    AnyMonoid().apply {
        require(mappend(true, mempty()))
        require(!mappend(false, mempty()))
    }
}

class AnyMonoid : Monoid<Boolean> {
    override fun mempty(): Boolean = false

    override fun mappend(m1: Boolean, m2: Boolean): Boolean = m1 || m2

}
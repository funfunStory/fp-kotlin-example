package fp.kotlin.example.chapter09.solution

import fp.kotlin.example.chapter09.Monoid

/**
 *
 * 연습문제 9-2
 *
 * ``&&`` 연산을 All 모노이드로 만들어보자.
 *
 */

fun main(args: Array<String>) {
    AllMonoid().run {
        require(mappend(true, mempty()))
        require(!mappend(false, mempty()))
    }
}

class AllMonoid : Monoid<Boolean> {
    override fun mempty(): Boolean = true

    override fun mappend(m1: Boolean, m2: Boolean): Boolean = m1 && m2

}
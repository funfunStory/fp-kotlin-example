package fp.kotlin.example.chapter09.exercise

import fp.kotlin.example.chapter09.Monoid

/**
 *
 * 연습문제 9-2
 *
 * ``&&`` 연산을 All 모노이드로 만들어보자.
 *®
 */


fun main(args: Array<String>) {
    val allMonoid : AallMonoid = AallMonoid()

    require(allMonoid.mappend(true, allMonoid.mempty()) == true)
    require(allMonoid.mappend(false, allMonoid.mempty()) == false)
}

class AallMonoid : Monoid<Boolean> {
    override fun mempty(): Boolean {
        TODO()
    }

    override fun mappend(m1: Boolean, m2: Boolean): Boolean {
        TODO()
    }

}
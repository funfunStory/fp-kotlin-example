package fp.kotlin.example.chapter09.exercise

import fp.kotlin.example.chapter09.Monoid

/**
 *
 * 연습문제 9-1
 *
 * ``||`` 연산을 Any 모노이드로 만들어보자.
 *
 */


fun main(args: Array<String>) {
    val anyMonoid: AnyMonoid = AnyMonoid()

    require(anyMonoid.mappend(true, anyMonoid.mempty()) == true)
    require(anyMonoid.mappend(false, anyMonoid.mempty()) == false)
}

class AnyMonoid : Monoid<Boolean> {
    override fun mempty(): Boolean {
        TODO()
    }

    override fun mappend(m1: Boolean, m2: Boolean): Boolean {
        TODO()
    }

}
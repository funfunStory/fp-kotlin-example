package fp.kotlin.example.chapter09.exercise

/**
 *
 * 연습문제 9-3
 *
 * Any 모노이드가 모노이드의 법칙을 만족하는지 테스트해보자.
 *
 */

fun main(args: Array<String>) {
    val x = true
    val y = false
    val z = true

    AnyMonoid().run {
        require(mappend(x, mempty()) == x)
        require(mappend(mempty(), x) == x)
        require(mappend(x, mappend(y, z)) == mappend(mappend(x, y), z))
    }
}
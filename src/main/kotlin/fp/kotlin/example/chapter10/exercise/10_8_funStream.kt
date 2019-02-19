package fp.kotlin.example.chapter10.exercise

/**
 *
 * 연습문제 10-8
 *
 * 새로운 FunList와 동일한 유사한 인터페이스의 FunStream를 만들어 보자.
 * 힌트: 5장에서 작성한 FunStream을 참고하라.
 */
fun main() {
}

sealed class FunStream<out A> {

    object Nil : FunStream<Nothing>()
    data class Cons<out A>(val head: () -> A, val tail: () -> FunStream<A>) : FunStream<A>() {
        override fun equals(other: Any?): Boolean =
            if (other is Cons<*>) {
                if (head() == other.head()) {
                    tail() == other.tail()
                } else {
                    false
                }
            } else {
                false
            }

        override fun hashCode(): Int {
            var result = head.hashCode()
            result = 31 * result + tail.hashCode()
            return result
        }
    }
}

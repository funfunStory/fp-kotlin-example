package fp.kotlin.example.chapter04.exercise

/**
 * 연습문제 4-1
 *
 * 코드 4-11에서 구현한 ``PartialFunction`` 클래스에 ``invokeOrElse``함수와 ``orElse`` 함수를 추가해보자. 각  함수의 프로토타입은 아래와 같다.
 *
 * fun invokeOrElse(p: P, default: R): R
 * fun orElse(that: PartialFunction<P, R>): PartialFunction<P, R>
 * ``invokeOrElse`` 함수는 입력 값 ``p``가 조건에 맞지 않을때 기본값 ``default``를 반환한다.
 * ``orElse`` 함수는 ``PartialFunction``의 입력값 ``p``가 조건에 맞으면 ``PartialFunction``을 그대로(this) 반환하고,
 * 조건에 맞지 않으면 ``that``을 반환한다.
 */

class PartialFunction<P, R>(
        private val condition: (P) -> Boolean,
        private val f: (P) -> R) :(P) -> R {

    override fun invoke(p: P): R {
        if (condition(p)) {
            return f(p)
        } else {
            throw IllegalArgumentException("$p isn't supported.")
        }
    }

    fun isDefinedAt(p: P): Boolean = condition(p)

    fun invokeOrElse(p: P, default: R): R = TODO()

    fun orElse(that: PartialFunction<P, R>): PartialFunction<P, R> =
            PartialFunction({ it: P -> this.isDefinedAt(it) || that.isDefinedAt(it) }, { it: P -> TODO() })
}

fun <P, R> ((P) -> R).toPartialFunction(definedAt: (P) -> Boolean)
        : PartialFunction<P, R> = PartialFunction(definedAt, this)

fun main() {
    val condition: (Int) -> Boolean = { 0 == it.rem(2) }
    val body: (Int) -> String = { "$it is even" }

    val isEven = body.toPartialFunction(condition)
    val isOdd = { i: Int -> "$i is odd" }.toPartialFunction{ !condition(it) }

    println(listOf(1, 2, 3).map { isEven.invokeOrElse(it, "$it is odd") })    // [1 is odd, 2 is even, 3 is odd]
    println(listOf(1, 2, 3).map { isEven.orElse(isOdd) })    // [1 is odd, 2 is even, 3 is odd]
}
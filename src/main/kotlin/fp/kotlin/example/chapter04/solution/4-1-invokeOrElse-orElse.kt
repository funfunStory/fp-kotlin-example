package fp.kotlin.example.chapter04.solution

/**
 * 연습문제 4-1
 *
 * 위에서 구현한 PartialFunction 클래스에 ``invokeOrElse``함수와 ``orElse`` 함수를 추가해보자. 각  함수의 프로토타입은 아래와 같다.
 * ``invokeOrElse`` 함수는 입력값이 조건에 맞지 않을때  결과값을 설정할 수 있다.
 * ``orElse`` 함수는 입력값의 조건이 맞지 않을때 수행할 함수를 설정할 수 있다.
 * 작성된 각 함수를 활용해서 isEven 예제에서 홀수인 경우도 출력하도록 테스트하라.
 *
 * 힌트: 함수의 선언 타입은 아래와 같다.
 * fun invokeOrElse(p: P, default: R): R
 * fun orElse(that: PartialFunction<P, R>): PartialFunction<P, R>
 */

fun main(args: Array<String>) {
    val condition: (Int) -> Boolean = { 0 == it.rem(2) }
    val body: (Int) -> String = { "$it is even" }

    val isEven = body.toPartialFunction(condition)
    val isOdd = { i: Int -> "$i is odd" }.toPartialFunction{ !condition(it) }

    println(listOf(1, 2, 3).map( isEven orElse isOdd ))    // [1 is odd, 2 is even, 3 is odd]
    println(listOf(1, 2, 3).map { isEven.invokeOrElse(it, "$it is odd") })    // [1 is odd, 2 is even, 3 is odd]
}

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

    fun invokeOrElse(p: P, default: R): R = if (isDefinedAt(p)) invoke(p) else default

    infix fun orElse(that: PartialFunction<P, R>): PartialFunction<P, R> =
            PartialFunction({ this.isDefinedAt(it) || that.isDefinedAt(it) }) {
                when {
                    this.isDefinedAt(it) -> this(it)
                    that.isDefinedAt(it) -> that(it)
                    else -> throw IllegalArgumentException("$it isn't defined")
                }
            }
}

fun <P, R> ((P) -> R).toPartialFunction(definedAt: (P) -> Boolean)
        : PartialFunction<P, R> = PartialFunction(definedAt, this)
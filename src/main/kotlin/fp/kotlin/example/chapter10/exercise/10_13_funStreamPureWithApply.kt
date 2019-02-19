package fp.kotlin.example.chapter10.exercise

/**
 *
 * 연습문제 10-13
 *
 * 5장에서 작성한 FunStream에 ``pure``, ``apply`` 함수를 추가해보자.
 *
 */
fun main() {

    val result = pure(1)
    require(result == funStreamOf(1))

    val funStream: FunStream<(Int) -> Int> = funStreamOf({ x -> x }, { x -> x * 2 }, { x -> x * 3 })
    val valueStream: FunStream<Int> = funStreamOf(1, 2, 3)
    val result2 = funStream apply valueStream

    require(result2 == funStreamOf(1, 2, 3, 2, 4, 6, 3, 6, 9))
}

fun <T> pure(value: T): FunStream<T> = TODO()

infix fun <A, B> FunStream<(A) -> B>.apply(f: FunStream<A>): FunStream<B> = TODO()
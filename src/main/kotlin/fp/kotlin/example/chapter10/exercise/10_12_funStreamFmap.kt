package fp.kotlin.example.chapter10.exercise

/**
 *
 * 연습문제 10-12
 *
 * 새로운 FunStream에 ``fmap`` 함수를 구현해보자.
 *
 */
fun main() {

    val funStream: FunStream<Int> = funStreamOf(1, 2, 3)
    val empty = mempty<Int>()
    val function: (Int) -> String = { "value : $it" }

    val result: FunStream<String> = funStream fmap function
    require(result == funStreamOf("value : 1", "value : 2", "value : 3"))

    val result2: FunStream<String> = empty fmap function
    require(result2 == mempty<String>())
}

infix fun <T, R> FunStream<T>.fmap(f: (T) -> R): FunStream<R> = TODO()
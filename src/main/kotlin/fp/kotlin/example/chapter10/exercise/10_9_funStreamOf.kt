package fp.kotlin.example.chapter10.exercise

/**
 *
 * 연습문제 10-9
 *
 * FunStream에 리스트 생성 함수 funStreamOf를 작성해보자.
 *
 * 힌트: 5장에서 작성한 FunStream을 참고하라.
 */
fun main() {

    val funStream: FunStream<Int> = funStreamOf(1, 2, 3)
    require(funStream == FunStream.Cons({ 1 }, { FunStream.Cons({ 2 }, { FunStream.Cons({ 3 }, { FunStream.Nil }) }) }))

    val nilStream = FunStream.Nil
    require(nilStream == FunStream.Nil)
}

fun <T> funStreamOf(vararg elements: T): FunStream<T> = TODO()

private fun <T> Array<out T>.toFunStream(): FunStream<T> = TODO()
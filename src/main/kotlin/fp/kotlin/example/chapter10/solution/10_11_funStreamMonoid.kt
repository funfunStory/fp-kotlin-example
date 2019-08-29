package fp.kotlin.example.chapter10.solution

/**
 *
 * 연습문제 10-11
 *
 * ``FunStream``을 모노이드로 작성해 보자.
 */
fun main() {

    val funStream: FunStream<Int> = funStreamOf(1, 2, 3)
    val funStream2: FunStream<Int> = funStreamOf(4, 5, 6)

    require(funStream mappend funStream2 == funStreamOf(1, 2, 3, 4, 5, 6))
    require(funStream mappend mempty() == funStreamOf(1, 2, 3))
    require(mempty<Int>() mappend funStream == funStreamOf(1, 2, 3))
    require(mempty<Int>() mappend mempty() == mempty<Int>())

}

fun <T> mempty(): FunStream<T> = FunStream.Nil

infix fun <A> FunStream<A>.mappend(other: FunStream<A>): FunStream<A> = when {
    this is FunStream.Nil -> other
    other is FunStream.Nil -> this
    this is FunStream.Cons && other is FunStream.Cons -> FunStream.Cons({ head() }, { tail().mappend(other) })
    else -> FunStream.Nil
}

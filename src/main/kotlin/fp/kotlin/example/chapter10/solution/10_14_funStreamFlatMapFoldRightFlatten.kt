package fp.kotlin.example.chapter10.solution

/**
 *
 * 연습문제 10-14
 *
 * FunStream에 ``flodRight``, ``flatten``,``flatMap`` 함수를 추가해보자. 게으른 평가로 동작해야 함.
 *
 */
fun main() {

    val valueStream: FunStream<Int> = funStreamOf(1, 2, 3)
    val functionStream: (Int) -> FunStream<Int> = { x ->
        funStreamOf(x, x * 2, x * 3)
    }
    val flatMapResult = valueStream flatMap functionStream
    require(flatMapResult == funStreamOf(1, 2, 3, 2, 4, 6, 3, 6, 9))

    val funStream: FunStream<FunStream<Int>> = funStreamOf(funStreamOf(1, 2), funStreamOf(3, 4), funStreamOf(5, 6))
    val flattenResult = funStream.flatten()
    require(flattenResult == funStreamOf(1, 2, 3, 4, 5, 6))

    val foldRightStream = funStreamOf(1, 2, 3)
    val foldRightResult = foldRightStream.foldRight(1) { x, acc -> x * acc }
    require(foldRightResult == 6)
}

fun <T> FunStream<FunStream<T>>.flatten(): FunStream<T> = when (this) {
    FunStream.Nil -> FunStream.Nil
    is FunStream.Cons -> head() mappend tail().flatten()
}

fun <T, R> FunStream<T>.foldRight(acc: R, f: (T, R) -> R): R = when (this) {
    FunStream.Nil -> acc
    is FunStream.Cons -> f(head(), tail().foldRight(acc, f))
}

infix fun <T, R> FunStream<T>.flatMap(f: (T) -> FunStream<R>): FunStream<R> = when (this) {
    FunStream.Nil -> FunStream.Nil
    is FunStream.Cons -> f(head()) mappend tail().flatMap(f)
}

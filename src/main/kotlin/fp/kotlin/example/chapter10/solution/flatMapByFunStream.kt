package fp.kotlin.example.chapter10.solution

// TODO: 일단 제가 11장 작성할때 대충 동작하는 애들이 필요해서 FunList에 이름만 바꾼 거에요~
// TODO: 게으른 평가로 동작하는 새로운 FunStream에 동작하도록 명인님께서 연습문제로 분리해서 작성해주세요~

sealed class FunStream<out T> {
    companion object

    object Nil : FunStream<kotlin.Nothing>() {
        override fun toString(): String = "[]"
    }

    data class Cons<out T>(val head: T, val tail: FunStream<T>) : FunStream<T>() {
        override fun toString(): String = "[${foldLeft("") { acc, x -> "$acc, $x" }.drop(2)}]"
    }
}

fun <T> funStreamOf(vararg elements: T): FunStream<T> = elements.toFunStream()

private fun <T> Array<out T>.toFunStream(): FunStream<T> = when {
    this.isEmpty() -> FunStream.Nil
    else -> FunStream.Cons(this[0], this.copyOfRange(1, this.size).toFunStream())
}

fun <T> FunStream<T>.mempty() = FunStream.Nil

infix fun <T> FunStream<T>.mappend(other: FunStream<T>): FunStream<T> = when (this) {
    is FunStream.Nil -> other
    is FunStream.Cons -> FunStream.Cons(head, tail.mappend(other))
}

fun <T> FunStream<FunStream<T>>.flatten(): FunStream<T> = foldRight(mempty()) { t, r: FunStream<T> -> t mappend r }

fun <T> FunStream.Companion.pure(value: T): FunStream<T> = FunStream.Cons(value, FunStream.Nil)

infix fun <T, R> FunStream<(T) -> R>.apply(f: FunStream<T>): FunStream<R> = when (this) {
    is FunStream.Nil -> FunStream.Nil
    is FunStream.Cons -> f.fmap(head) mappend tail.apply(f)
}

infix fun <T, R> FunStream<T>._apply(f: FunStream<(T) -> R>): FunStream<R> = when (this) {
    is FunStream.Nil -> FunStream.Nil
    is FunStream.Cons -> f.fmap { it(head) } mappend tail._apply(f)
}

infix fun <T, R> FunStream<T>.flatMap(f: (T) -> FunStream<R>): FunStream<R> = fmap(f).flatten()

infix fun <T, R> FunStream<T>.fmap(f: (T) -> R): FunStream<R> = when (this) {
    is FunStream.Nil -> FunStream.Nil
    is FunStream.Cons -> FunStream.Cons(f(head), tail.fmap(f))
}

fun <T, R> FunStream<T>.foldRight(acc: R, f: (T, R) -> R): R = when (this) {
    is FunStream.Nil -> acc
    is FunStream.Cons -> f(head, tail.foldRight(acc, f))
}

tailrec fun <T, R> FunStream<T>.foldLeft(acc: R, f: (R, T) -> R): R = when (this) {
    is FunStream.Nil -> acc
    is FunStream.Cons -> tail.foldLeft(f(acc, head), f)
}

tailrec fun <T> FunStream<T>.contains(element: T): Boolean = when (this) {
    is FunStream.Nil -> false
    is FunStream.Cons -> if (element == head) true else tail.contains(element)
}

fun <T> FunStream<T>.distinct(): FunStream<T> =
        foldLeft(FunStream.Nil as FunStream<T>) { acc, x -> if (acc.contains(x)) acc else FunStream.Cons(x, acc) }

tailrec fun <T> FunStream<T>.reverse(acc: FunStream<T> = FunStream.Nil): FunStream<T> = when (this) {
    is FunStream.Nil -> acc
    is FunStream.Cons -> tail.reverse(FunStream.Cons(head, acc))
}

tailrec fun <T> FunStream<T>.filter(acc: FunStream<T> = FunStream.Nil, p: (T) -> Boolean): FunStream<T> = when (this) {
    is FunStream.Nil -> acc.reverse()
    is FunStream.Cons -> if (p(head)) {
        tail.filter(FunStream.Cons(head, acc), p)
    } else {
        tail.filter(acc, p)
    }
}

fun<T> printFunStream(list: FunStream<T>) = println(list.toStringByFoldLeft())

private fun <T> FunStream<T>.toStringByFoldLeft(): String = "[${foldLeft("") { acc, x -> "$acc, $x" }.drop(2)}]"
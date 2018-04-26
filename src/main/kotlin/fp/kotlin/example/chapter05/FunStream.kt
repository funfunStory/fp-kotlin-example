package fp.kotlin.example.chapter05

sealed class FunStream<out T> {
    object Nil : FunStream<Nothing>()
    data class Cons<out T>(val head: () -> T, val tail: () -> FunStream<T>) : FunStream<T>()
}

fun <T> funStreemOf(vararg elements: T): FunStream<T> =
    if (elements.isEmpty()) {
        FunStream.Nil
    } else {
        elements.fold(FunStream.Nil as FunStream<T>, { acc, elm -> acc.appendTail(elm) })
    }

fun <T> generateFunStream(seed: T, generate: (T) -> T): FunStream<T> =
    FunStream.Cons({ seed }, { generateFunStream(generate(seed), generate) })

fun <T> FunStream<T>.toFunList(): FunList<T> = when (this) {
    FunStream.Nil -> FunList.Nil
    else -> FunList.Cons(this.getHead(), this.getTail().toFunList())
}

fun FunStream<Int>.sum(): Int = when (this) {
    FunStream.Nil -> 0
    is FunStream.Cons -> head() + tail().sum()
}

fun FunStream<Double>.product(): Double = when (this) {
    FunStream.Nil -> 1.0
    is FunStream.Cons -> if (head() == 0.0) 0.0 else head() * tail().product()
}

fun <T> FunStream<T>.getHead(): T = when (this) {
    FunStream.Nil -> throw NullPointerException()
    is FunStream.Cons -> this.head()
}

fun <T> FunStream<T>.getTail(): FunStream<T> = when (this) {
    FunStream.Nil -> throw NullPointerException()
    is FunStream.Cons -> this.tail()
}

fun <T> FunStream<T>.appendTail(value: T): FunStream<T> = when (this) {
    FunStream.Nil -> FunStream.Cons({ value }, { FunStream.Nil })
    is FunStream.Cons -> FunStream.Cons(this.head, { this.tail().appendTail(value) })
}

fun <T> FunStream<T>.last(): T = when (this) {
    FunStream.Nil -> throw NullPointerException()
    is FunStream.Cons -> if (this.tail() === FunStream.Nil) {
        this.head()
    } else {
        this.tail().last()
    }
}

tailrec fun <T> FunStream<T>.take(n: Int, acc: FunStream<T> = FunStream.Nil): FunStream<T> = when {
    n < 0 -> throw IllegalArgumentException()
    n == 0 -> acc
    else -> when (this) {
        FunStream.Nil -> acc
        is FunStream.Cons -> getTail().take(n - 1, acc.appendTail(getHead()))
    }

}
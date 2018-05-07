package fp.kotlin.example.chapter05

sealed class FunStream<out T> {
    object Nil : FunStream<Nothing>()
    data class Cons<out T>(val head: () -> T, val tail: () -> FunStream<T>) : FunStream<T>()
}

fun <T> funStreamOf(vararg elements: T): FunStream<T> = elements.toFunStream()

private tailrec fun <T> Array<out T>.toFunStream(acc: FunStream<T> = FunStream.Nil): FunStream<T> = when {
    this.isEmpty() -> acc
    else -> this.copyOfRange(1, this.size).toFunStream(acc.appendTail(this[0]))
}

fun <T> generateFunStream(seed: T, generate: (T) -> T): FunStream<T> =
    FunStream.Cons({ seed }, { generateFunStream(generate(seed), generate) })

fun <T> FunStream<T>.toFunList(): FunList<T> = when (this) {
    FunStream.Nil -> FunList.Nil
    else -> FunList.Cons(getHead(), getTail().toFunList())
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
    is FunStream.Cons -> head()
}

fun <T> FunStream<T>.getTail(): FunStream<T> = when (this) {
    FunStream.Nil -> throw NullPointerException()
    is FunStream.Cons -> tail()
}

fun <T> FunStream<T>.appendTail(value: T): FunStream<T> = when (this) {
    FunStream.Nil -> FunStream.Cons({ value }, { FunStream.Nil })
    is FunStream.Cons -> FunStream.Cons(head, { tail().appendTail(value) })
}

tailrec fun <T> FunStream<T>.filter(acc: FunStream<T> = FunStream.Nil, f: (T) -> Boolean): FunStream<T> = when (this) {
    FunStream.Nil -> acc
    is FunStream.Cons -> if (f(head())) {
        tail().filter(acc.appendTail(head()), f)
    } else {
        tail().filter(acc, f)
    }
}

tailrec fun <T, R> FunStream<T>.map(acc: FunStream<R> = FunStream.Nil, f: (T) -> R): FunStream<R> = when (this) {
    FunStream.Nil -> acc
    is FunStream.Cons -> tail().map(acc.appendTail(f(head())), f)
}

tailrec fun <T> FunStream<T>.drop(n: Int): FunStream<T> = when {
    n == 0 || this === FunStream.Nil -> this
    else -> getTail().drop(n - 1)
}

tailrec fun <T> FunStream<T>.take(n: Int, acc: FunStream<T> = FunStream.Nil): FunStream<T> = when {
    n < 0 -> throw IllegalArgumentException()
    n == 0 -> acc
    else -> when (this) {
        FunStream.Nil -> acc
        is FunStream.Cons -> tail().take(n - 1, acc.appendTail(getHead()))
    }
}

tailrec fun <T> FunStream<T>.forEach(f: (T)-> Unit): Unit = when(this) {
    FunStream.Nil -> Unit
    is FunStream.Cons -> {
        f(head())
        tail().forEach(f)
    }
}
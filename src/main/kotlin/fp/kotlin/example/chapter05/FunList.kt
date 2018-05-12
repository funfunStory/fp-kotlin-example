package fp.kotlin.example.chapter05

import fp.kotlin.example.chapter05.solution.appendTail

sealed class FunList<out T> {
    object Nil : FunList<Nothing>()
    data class Cons<out T>(val head: T, val tail: FunList<T>) : FunList<T>()
}

fun <T> funListOf(vararg elements: T): FunList<T> = elements.toFunList()

private fun <T> Array<out T>.toFunList(): FunList<T> = when {
    this.isEmpty() -> FunList.Nil
    else -> FunList.Cons(this[0], this.copyOfRange(1, this.size).toFunList())
}

fun <T> FunList<T>.toFunStream(): FunStream<T> = when (this) {
    FunList.Nil -> FunStream.Nil
    else -> FunStream.Cons({ this.getHead() }, { this.getTail().toFunStream() })
}

fun IntProgression.toFunList(): FunList<Int> = when {
    first > last -> FunList.Nil
    else -> FunList.Cons(first, ((first + step)..last step step).toFunList())
}

fun FunList<Double>.product(): Double = when (this) {
    FunList.Nil -> 1.0
    is FunList.Cons -> if (head == 0.0) 0.0 else head * tail.product()
}

fun <T> FunList<T>.getTail(): FunList<T> = when (this) {
    FunList.Nil -> throw NullPointerException()
    is FunList.Cons -> tail
}

fun <T> FunList<T>.getHead(): T = when (this) {
    FunList.Nil -> throw NullPointerException()
    is FunList.Cons -> head
}

fun <T> FunList<T>.addHead(head: T): FunList<T> = FunList.Cons(head, this)

fun FunList<Int>.sum(): Int = foldLeft(0) { acc, x -> acc + x }

fun <T> FunList<T>.filter(f: (T) -> Boolean): FunList<T> = when (this) {
    FunList.Nil -> this
    is FunList.Cons -> if (f(head)) {
        FunList.Cons(head, tail.filter(f))
    } else {
        tail.filter(f)
    }
}

tailrec fun <T> FunList<T>.drop(n: Int): FunList<T> = when {
    n == 0 || this === FunList.Nil -> this
    else -> getTail().drop(n - 1)
}

fun <T> FunList<T>.take(n: Int, acc: FunList<T> = FunList.Nil): FunList<T> = when {
    n == 0 || this === FunList.Nil -> acc
    else -> getTail().take(n - 1, acc.addHead(getHead()))
}

fun <T, R> FunList<T>.map(f: (T) -> R): FunList<R> = when (this) {
    FunList.Nil -> FunList.Nil
    is FunList.Cons -> FunList.Cons(f(head), tail.map(f))
}

tailrec fun <T, R> FunList<T>.foldLeft(acc: R, f: (R, T) -> R): R = when (this) {
    FunList.Nil -> acc
    is FunList.Cons -> tail.foldLeft(f(acc, head), f)
}

fun <T, R> FunList<T>.foldRight(acc: R, f: (T, R) -> R): R = when (this) {
    FunList.Nil -> acc
    is FunList.Cons -> f(head, tail.foldRight(acc, f))
}

fun <T, R> FunList<T>.mapByFoldLeft(f: (T) -> R): FunList<R> =
    foldLeft(FunList.Nil) { acc: FunList<R>, x ->
        acc.appendTail(f(x))
    }

fun <T, R> FunList<T>.mapByFoldRight(f: (T) -> R): FunList<R> =
    foldRight(FunList.Nil) { x, acc: FunList<R> ->
        acc.addHead(f(x))
    }

fun <T1, T2, R> FunList<T1>.zipWith(f: (T1, T2) -> R, list: FunList<T2>): FunList<R> = when {
    this === FunList.Nil || list === FunList.Nil -> FunList.Nil
    else -> FunList.Cons(f(getHead(), list.getHead()), getTail().zipWith(f, list.getTail()))
}
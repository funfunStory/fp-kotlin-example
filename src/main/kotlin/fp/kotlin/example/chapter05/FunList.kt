package fp.kotlin.example.chapter05

import fp.kotlin.example.chapter05.solution.appendTail

sealed class FunList<out T> {

    object Nil : FunList<Nothing>()
    data class Cons<T>(val head: T, var tail: FunList<T>) : FunList<T>()
}

fun <T> funListOf(vararg elements: T): FunList<T> =
    if (elements.isEmpty()) {
        FunList.Nil
    } else {
        elements.fold(FunList.Nil as FunList<T>, { acc, elm -> acc.appendTail(elm) })
    }

fun <T> FunList<T>.toFunStream(): FunStream<T> = when (this) {
    FunList.Nil -> FunStream.Nil
    else -> FunStream.Cons({ this.getHead() }, { this.getTail().toFunStream() })
}

fun FunList<Int>.sum(): Int = when (this) {
    FunList.Nil -> 0
    is FunList.Cons -> head + tail.sum()
}

fun FunList<Double>.product(): Double = when (this) {
    FunList.Nil -> 1.0
    is FunList.Cons -> if (head == 0.0) 0.0 else head * tail.product()
}

fun <T> FunList<T>.getTail(): FunList<T> = when (this) {
    FunList.Nil -> this
    is FunList.Cons -> tail
}

fun <T> FunList<T>.getHead(): T = when (this) {
    FunList.Nil -> throw NullPointerException()
    is FunList.Cons -> head
}

fun <T> FunList<T>.addHead(head: T): FunList<T> = FunList.Cons(head, this)

fun FunList<Int>.sum(): Int = this.foldLeft(0) { acc, x -> acc + x }

tailrec fun <T> FunList<T>.filter(acc: FunList<T> = FunList.Nil, f: (T) -> Boolean): FunList<T> = when (this) {
    FunList.Nil -> acc
    is FunList.Cons -> if (f(head)) {
        tail.filter(acc.appendTail(head), f)
    } else {
        tail.filter(acc, f)
    }
}

tailrec fun <T> FunList<T>.drop(n: Int): FunList<T> = when {
    n == 0 || this === FunList.Nil -> this
    else -> getTail().drop(n - 1)
}

tailrec fun <T> FunList<T>.take(n: Int, acc: FunList<T> = FunList.Nil): FunList<T> = when {
    n == 0 || this === FunList.Nil -> acc
    else -> getTail().take(n - 1, acc.appendTail(getHead()))
}

tailrec fun <T, R> FunList<T>.map(acc: FunList<R> = FunList.Nil, f: (T) -> R): FunList<R> = when (this) {
    FunList.Nil -> acc
    is FunList.Cons -> tail.map(acc.appendTail(f(head)), f)
}

tailrec fun <T, R> FunList<T>.foldLeft(acc: R, f: (R, T) -> R): R = when (this) {
    FunList.Nil -> acc
    is FunList.Cons -> tail.foldLeft(f(acc, head), f)
}

fun <T, R> FunList<T>.foldRight(acc: R, f: (T, R) -> R): R = when (this) {
    FunList.Nil -> acc
    is FunList.Cons -> f(head, tail.foldRight(acc, f))
}

fun <T, R> FunList<T>.mapByFoldLeft(f: (T) -> R): FunList<R> = this.foldLeft(FunList.Nil) {
    acc: FunList<R>, x -> acc.appendTail(f(x))
}

fun <T, R> FunList<T>.mapByFoldRight(f: (T) -> R): FunList<R> = this.foldRight(FunList.Nil) {
    x, acc: FunList<R> -> acc.addHead(f(x))
}

tailrec fun <T, R> FunList<T>.zip(list: FunList<R>, acc: FunList<Pair<T, R>> = FunList.Nil): FunList<Pair<T, R>> = when {
    this === FunList.Nil || list === FunList.Nil -> acc
    else -> this.getTail().zip(list.getTail(), acc.appendTail(this.getHead() to list.getHead()))
}

fun <T1, T2, R> FunList<T1>.zipWith(f: (T1, T2) -> R, list: FunList<T2>, acc: FunList<R> = FunList.Nil): FunList<R> = when {
    this === FunList.Nil || list === FunList.Nil -> acc
    else -> this.getTail().zipWith(f, list.getTail(), acc.appendTail(f(this.getHead(), list.getHead())))
}
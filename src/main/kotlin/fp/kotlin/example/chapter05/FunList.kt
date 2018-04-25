package fp.kotlin.example.chapter05

import fp.kotlin.example.chapter05.solution.appendTail

sealed class FunList<out T> {

    object Nil : FunList<Nothing>()
    data class Cons<T>(val head: T, var tail: FunList<T>) : FunList<T>()
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

tailrec fun FunList<Int>.add2(acc: FunList<Int> = FunList.Nil): FunList<Int> = when (this) {
    FunList.Nil -> acc
    is FunList.Cons -> tail.add2(acc.appendTail(head + 2))
}

tailrec fun FunList<Double>.product2(acc: FunList<Double> = FunList.Nil): FunList<Double> = when (this) {
    FunList.Nil -> acc
    is FunList.Cons -> tail.product2(acc.appendTail(head * 2))
}

tailrec fun <T, R> FunList<T>.map(acc: FunList<R> = FunList.Nil, f: (T) -> R): FunList<R> = when (this) {
    FunList.Nil -> acc
    is FunList.Cons -> tail.map(acc.appendTail(f(head)), f)
}

tailrec fun <T, R> FunList<T>.foldLeft(acc: R, f: (R, T) -> R): R = when (this) {
    FunList.Nil -> acc
    is FunList.Cons -> tail.foldLeft(f(acc, head), f)
}

tailrec fun <T, R> FunList<T>.zip(list: FunList<R>,
    acc: FunList<Pair<T, R>> = FunList.Nil): FunList<Pair<T, R>> = when {
    this === FunList.Nil || list === FunList.Nil -> acc
    else -> this.getTail().zip(list.getTail(), acc.appendTail(this.getHead() to list.getHead()))
}

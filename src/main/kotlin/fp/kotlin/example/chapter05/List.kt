package fp.kotlin.example.chapter05

sealed class List<out T> {

    object Nil : List<Nothing>()
    data class Cons<T>(val head: T, var tail: List<T>) : List<T>()
}

fun List<Int>.sum(): Int = when (this) {
    List.Nil -> 0
    is List.Cons -> head + tail.sum()
}

fun List<Double>.product(): Double = when (this) {
    List.Nil -> 1.0
    is List.Cons -> if (head == 0.0) 0.0 else head * tail.product()
}

fun <T> List<T>.getTail(): List<T> = when (this) {
    List.Nil -> throw NullPointerException()
    is List.Cons -> tail
}

fun <T> List<T>.getHead(): T = when (this) {
    List.Nil -> throw NullPointerException()
    is List.Cons -> head
}

fun <T> List<T>.appendTail(value: T): List<T> = when (this) {
    List.Nil -> List.Cons(value,
        List.Nil)
    is List.Cons -> List.Cons(head,
        tail.appendTail(value))
}

fun <T> List<T>.addHead(head: T): List<T> = List.Cons(
    head, this)

tailrec fun <T> List<T>.filter(f: (T) -> Boolean, acc: List<T> = List.Nil): List<T> = when (this) {
    List.Nil -> acc
    is List.Cons -> if (f(head)) {
        tail.filter(f, acc.appendTail(head))
    } else {
        tail.filter(f, acc)
    }
}

tailrec fun <T> List<T>.drop(n: Int): List<T> = when {
    n == 0 || this is List.Nil -> this
    else -> getTail().drop(n - 1)
}

tailrec fun <T> List<T>.take(n: Int, acc: List<T> = List.Nil): List<T> = when {
    n == 0 || this is List.Nil -> acc
    else -> getTail().take(n - 1, acc.appendTail(getHead()))
}

tailrec fun List<Int>.add2(acc: List<Int> = List.Nil): List<Int> = when (this) {
    List.Nil -> acc
    is List.Cons -> tail.add2(acc.appendTail(head + 2))
}

tailrec fun List<Double>.product2(acc: List<Double> = List.Nil): List<Double> = when (this) {
    List.Nil -> acc
    is List.Cons -> tail.product2(acc.appendTail(head * 2))
}

tailrec fun <T, R> List<T>.map(f: (T) -> R, acc: List<R> = List.Nil): List<R> = when (this) {
    List.Nil -> acc
    is List.Cons -> tail.map(f, acc.appendTail(f(head)))
}

tailrec fun <T, R> List<T>.foldLeft(f: (R, T) -> R, acc: R): R = when (this) {
    List.Nil -> acc
    is List.Cons -> tail.foldLeft(f, f(acc, head))
}

tailrec fun <T, R> List<T>.zip(list: List<R>, acc: List<Pair<T, R>> = List.Nil): List<Pair<T, R>> = when {
    this === List.Nil || list === List.Nil -> acc
    else -> this.getTail().zip(list.getTail(), acc.appendTail(this.getHead() to list.getHead()))
}

package fp.kotlin.example.chapter05

sealed class fList<out T> {

    object Nil : fList<Nothing>()
    data class Cons<T>(val head: T, var tail: fList<T>) : fList<T>()
}

fun fList<Int>.sum(): Int = when (this) {
    fList.Nil -> 0
    is fList.Cons -> head + tail.sum()
}

fun fList<Double>.product(): Double = when (this) {
    fList.Nil -> 1.0
    is fList.Cons -> if (head == 0.0) 0.0 else head * tail.product()
}

fun <T> fList<T>.getTail(): fList<T> = when (this) {
    fList.Nil -> throw NullPointerException()
    is fList.Cons -> tail
}

fun <T> fList<T>.getHead(): T = when (this) {
    fList.Nil -> throw NullPointerException()
    is fList.Cons -> head
}

fun <T> fList<T>.appendTail(value: T): fList<T> = when (this) {
    fList.Nil -> fList.Cons(value,
        fList.Nil)
    is fList.Cons -> fList.Cons(head,
        tail.appendTail(value))
}

fun <T> fList<T>.addHead(head: T): fList<T> = fList.Cons(
    head, this)

tailrec fun <T> fList<T>.filter(acc: fList<T> = fList.Nil, f: (T) -> Boolean): fList<T> = when (this) {
    fList.Nil -> acc
    is fList.Cons -> if (f(head)) {
        tail.filter(acc.appendTail(head), f)
    } else {
        tail.filter(acc, f)
    }
}

tailrec fun <T> fList<T>.drop(n: Int): fList<T> = when {
    n == 0 || this is fList.Nil -> this
    else -> getTail().drop(n - 1)
}

tailrec fun <T> fList<T>.take(n: Int, acc: fList<T> = fList.Nil): fList<T> = when {
    n == 0 || this is fList.Nil -> acc
    else -> getTail().take(n - 1, acc.appendTail(getHead()))
}

tailrec fun fList<Int>.add2(acc: fList<Int> = fList.Nil): fList<Int> = when (this) {
    fList.Nil -> acc
    is fList.Cons -> tail.add2(acc.appendTail(head + 2))
}

tailrec fun fList<Double>.product2(acc: fList<Double> = fList.Nil): fList<Double> = when (this) {
    fList.Nil -> acc
    is fList.Cons -> tail.product2(acc.appendTail(head * 2))
}

tailrec fun <T, R> fList<T>.map(acc: fList<R> = fList.Nil, f: (T) -> R): fList<R> = when (this) {
    fList.Nil -> acc
    is fList.Cons -> tail.map(acc.appendTail(f(head)), f)
}

tailrec fun <T, R> fList<T>.foldLeft(acc: R, f: (R, T) -> R): R = when (this) {
    fList.Nil -> acc
    is fList.Cons -> tail.foldLeft(f(acc, head), f)
}

tailrec fun <T, R> fList<T>.zip(list: fList<R>, acc: fList<Pair<T, R>> = fList.Nil): fList<Pair<T, R>> = when {
    this === fList.Nil || list === fList.Nil -> acc
    else -> this.getTail().zip(list.getTail(), acc.appendTail(this.getHead() to list.getHead()))
}

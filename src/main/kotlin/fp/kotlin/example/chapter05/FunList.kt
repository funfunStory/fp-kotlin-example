package fp.kotlin.example.chapter05

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

tailrec fun <T> FunList<T>.appendTail(value: T, acc: FunList<T> = FunList.Nil): FunList<T> = when (this) {
    FunList.Nil -> FunList.Cons(value, acc).reverse()
    is FunList.Cons -> tail.appendTail(value, acc.addHead(head))
}

fun FunList<Int>.sum(): Int = foldLeft(0) { acc, x -> acc + x }

fun <T> FunList<T>.filter(f: (T) -> Boolean): FunList<T> = when (this) {
    FunList.Nil -> this
    is FunList.Cons -> if (f(head)) {
        FunList.Cons(head, tail.filter(f))
    } else {
        tail.filter(f)
    }
}

fun <T, R> FunList<T>.map(f: (T) -> R): FunList<R> = when (this) {
    FunList.Nil -> FunList.Nil
    is FunList.Cons -> FunList.Cons(f(head), tail.map(f))
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

tailrec fun <T, R> FunList<T>.mapViaTailrec(acc: FunList<R> = FunList.Nil, f: (T) -> R): FunList<R> = when (this) {
    FunList.Nil -> acc.reverse()
    is FunList.Cons -> tail.mapViaTailrec(acc.addHead(f(head)), f)
}

tailrec fun <T> FunList<T>.filterViaTailrec(acc: FunList<T> = FunList.Nil,
    f: (T) -> Boolean): FunList<T> = when (this) {
    FunList.Nil -> acc.reverse()
    is FunList.Cons -> if (f(head)) {
        tail.filterViaTailrec(acc.addHead(head), f)
    } else {
        tail.filterViaTailrec(acc, f)
    }
}

tailrec fun <T> FunList<T>.reverse(acc: FunList<T> = FunList.Nil): FunList<T> = when (this) {
    FunList.Nil -> acc
    is FunList.Cons -> tail.reverse(acc.addHead(head))
}

tailrec fun <T, R> FunList<T>.foldLeft(acc: R, f: (R, T) -> R): R = when (this) {
    FunList.Nil -> acc
    is FunList.Cons -> tail.foldLeft(f(acc, head), f)
}

tailrec fun IntProgression.toFunListViaTailrec(acc: FunList<Int> = FunList.Nil): FunList<Int> = when {
    first > last -> acc
    else -> ((first + step)..last step step).toFunListViaTailrec(acc.addHead(first))
}
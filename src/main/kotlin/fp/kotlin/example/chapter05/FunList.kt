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

fun FunList<Double>.product(): Double = when (this) {
    FunList.Nil -> 1.0
    is FunList.Cons -> if (head == 0.0) 0.0 else head * tail.product()
}

fun <T> FunList<T>.getTail(): FunList<T> = when (this) {
    FunList.Nil -> throw NoSuchElementException()
    is FunList.Cons -> tail
}

fun <T> FunList<T>.getHead(): T = when (this) {
    FunList.Nil -> throw NoSuchElementException()
    is FunList.Cons -> head
}

fun <T> FunList<T>.addHead(head: T): FunList<T> = FunList.Cons(head, this)

infix fun <T> FunList<T>.concat(value: FunList<T>): FunList<T> {
    tailrec fun innerAppend(listA: FunList<T>, listB: FunList<T>): FunList<T> = when (listA) {
        FunList.Nil -> listB
        is FunList.Cons -> innerAppend(listA.tail, listB.addHead(listA.head))
    }
    return innerAppend(this.reverse(), value)
}

tailrec fun <T> FunList<T>.appendTail(value: T, acc: FunList<T> = FunList.Nil): FunList<T> = when (this) {
    FunList.Nil -> FunList.Cons(value, acc).reverse()
    is FunList.Cons -> tail.appendTail(value, acc.addHead(head))
}

fun FunList<Int>.sum(): Int = foldLeft(0) { acc, x -> acc + x }

tailrec fun <T> FunList<T>.filter(acc: FunList<T> = FunList.Nil, p: (T) -> Boolean): FunList<T> = when (this) {
    FunList.Nil -> acc.reverse()
    is FunList.Cons -> if (p(head)) {
        tail.filter(acc.addHead(head), p)
    } else {
        tail.filter(acc, p)
    }
}

tailrec fun <T, R> FunList<T>.map(acc: FunList<R> = FunList.Nil, f: (T) -> R): FunList<R> = when (this) {
    FunList.Nil -> acc.reverse()
    is FunList.Cons -> tail.map(acc.addHead(f(head)), f)
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

tailrec fun <T1, T2, R> FunList<T1>.zipWith(f: (T1, T2) -> R, list: FunList<T2>,
    acc: FunList<R> = FunList.Nil): FunList<R> = when {
    this === FunList.Nil || list === FunList.Nil -> acc.reverse()
    else -> getTail().zipWith(f, list.getTail(), acc.addHead(f(getHead(), list.getHead())))
}

tailrec fun <T> FunList<T>.reverse(acc: FunList<T> = FunList.Nil): FunList<T> = when (this) {
    FunList.Nil -> acc
    is FunList.Cons -> tail.reverse(acc.addHead(head))
}

tailrec fun <T, R> FunList<T>.foldLeft(acc: R, f: (R, T) -> R): R = when (this) {
    FunList.Nil -> acc
    is FunList.Cons -> tail.foldLeft(f(acc, head), f)
}

tailrec fun IntProgression.toFunList(acc: FunList<Int> = FunList.Nil): FunList<Int> = when {
    step > 0 -> when {
        first > last -> acc.reverse()
        else -> ((first + step)..last step step).toFunList(acc.addHead(first))
    }
    else -> when {
        first >= last -> {
            IntProgression.fromClosedRange(first + step, last, step).toFunList(acc.addHead(first))
        }
        else -> {
            acc.reverse()
        }
    }
}

tailrec fun <T> FunList<T>.forEach(f: (T) -> Unit): Unit = when (this) {
    FunList.Nil -> Unit
    is FunList.Cons -> {
        f(head)
        tail.forEach(f)
    }
}
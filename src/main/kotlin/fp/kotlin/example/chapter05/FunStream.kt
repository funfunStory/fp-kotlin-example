package fp.kotlin.example.chapter05

import fp.kotlin.example.chapter05.solution.appendTail

sealed class FunStream<out T> {
    object Nil : FunStream<Nothing>()
    data class Cons<out T>(val head: () -> T, val tail: () -> FunStream<T>) : FunStream<T>() {
        override fun equals(other: Any?): Boolean =
            if (other is Cons<*>) {
                if (head() == other.head()) {
                    tail() == other.tail()
                } else {
                    false
                }
            } else {
                false
            }

        override fun hashCode(): Int {
            var result = head.hashCode()
            result = 31 * result + tail.hashCode()
            return result
        }
    }
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

fun IntProgression.toFunStream(): FunStream<Int> = when {
    first > last -> FunStream.Nil
    else -> FunStream.Cons({ first }, { ((first + step)..last step step).toFunStream() })
}

fun FunStream<Double>.product(): Double = when (this) {
    FunStream.Nil -> 1.0
    is FunStream.Cons -> if (head() == 0.0) 0.0 else head() * tail().product()
}

tailrec fun <T> FunStream<T>.last(): T = when (this) {
    FunStream.Nil -> throw NullPointerException()
    is FunStream.Cons -> if (tail() === FunStream.Nil) {
        this.getHead()
    } else {
        tail().last()
    }
}

fun <T> FunStream<T>.getHead(): T = when (this) {
    FunStream.Nil -> throw NullPointerException()
    is FunStream.Cons -> head()
}

fun <T> FunStream<T>.getTail(): FunStream<T> = when (this) {
    FunStream.Nil -> throw NullPointerException()
    is FunStream.Cons -> tail()
}

fun <T> FunStream<T>.addHead(value: T): FunStream<T> = when (this) {
    FunStream.Nil -> FunStream.Cons({ value }, { FunStream.Nil })
    is FunStream.Cons -> FunStream.Cons(head, { this })
}

fun <T> FunStream<T>.filter(f: (T) -> Boolean): FunStream<T> = when (this) {
    FunStream.Nil -> FunStream.Nil
    is FunStream.Cons ->
        if (f(head())) {
            FunStream.Cons({ head() }, { tail().filter(f) })
        } else {
            FunStream.Cons({ this.dropWhile(f).getHead() }, { this.dropWhile(f).getTail().filter(f) })
        }
}

fun <T, R> FunStream<T>.map(f: (T) -> R): FunStream<R> = when (this) {
    FunStream.Nil -> FunStream.Nil
    is FunStream.Cons -> FunStream.Cons({ f(head()) }, { tail().map(f) })
}

tailrec fun <T> FunStream<T>.drop(n: Int): FunStream<T> = when {
    n <= 0 || this === FunStream.Nil -> FunStream.Nil
    else -> getTail().drop(n - 1)
}

tailrec fun <T> FunStream<T>.dropWhile(f: (T) -> Boolean): FunStream<T> = when (this) {
    FunStream.Nil -> this
    is FunStream.Cons -> if (f(head())) {
        this
    } else {
        tail().dropWhile(f)
    }
}

fun <T> FunStream<T>.take(n: Int): FunStream<T> = when {
    n < 0 -> throw IllegalArgumentException()
    n == 0 -> FunStream.Nil
    else -> when (this) {
        FunStream.Nil -> FunStream.Nil
        is FunStream.Cons -> FunStream.Cons({ head() }, { tail().take(n - 1) })
    }
}

tailrec fun <T> FunStream<T>.forEach(f: (T) -> Unit): Unit = when (this) {
    FunStream.Nil -> Unit
    is FunStream.Cons -> {
        f(head())
        tail().forEach(f)
    }
}
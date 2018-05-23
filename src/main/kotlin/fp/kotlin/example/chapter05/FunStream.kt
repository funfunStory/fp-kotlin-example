package fp.kotlin.example.chapter05

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

fun <T> Array<out T>.toFunStream(acc: FunStream<T> = FunStream.Nil): FunStream<T> = when {
    this.isEmpty() -> FunStream.Nil
    else -> FunStream.Cons({ this[0] }, { this.copyOfRange(1, this.size).toFunStream() })
}

fun <T> generateFunStream(seed: T, generate: (T) -> T): FunStream<T> =
    FunStream.Cons({ seed }, { generateFunStream(generate(seed), generate) })

fun <T> FunStream<T>.toFunList(): FunList<T> = when (this) {
    FunStream.Nil -> FunList.Nil
    else -> FunList.Cons(getHead(), getTail().toFunList())
}

fun IntProgression.toFunStream(): FunStream<Int> = when {
    step > 0 -> when {
        first > last -> FunStream.Nil
        else -> FunStream.Cons({ first }, { ((first + step)..last step step).toFunStream() })
    }
    else -> when {
        first >= last -> {
            FunStream.Cons({ first },
                { IntProgression.fromClosedRange(first + step, last, step).toFunStream() })
        }
        else -> {
            FunStream.Nil
        }
    }
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

fun <T> FunStream<T>.addHead(value: T): FunStream<T> = FunStream.Cons({ value }, { this })

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

tailrec fun <T, R> FunStream<T>.foldLeft(acc: R, f: (R, T) -> R): R = when (this) {
    FunStream.Nil -> acc
    is FunStream.Cons -> tail().foldLeft(f(acc, head()), f)
}

tailrec fun <T> FunStream<T>.forEach(f: (T) -> Unit): Unit = when (this) {
    FunStream.Nil -> Unit
    is FunStream.Cons -> {
        f(head())
        tail().forEach(f)
    }
}
package fp.kotlin.example.chapter05.solution

sealed class List<out T> {

    object Nil : List<Nothing>()
    data class Cons<T>(val head: T, var tail: List<T>) : List<T>()

    companion object {

        fun sum(ints: List<Int>): Int = when (ints) {
            Nil -> 0
            is Cons -> ints.head + sum(
                ints.tail)
        }

        fun product(doubles: List<Double>): Double = when (doubles) {
            Nil -> 1.0
            is Cons -> if (doubles.head == 0.0) 0.0 else doubles.head * product(
                doubles.tail)
        }

        fun <T> getTail(list: List<T>): List<T> = when (list) {
            Nil -> throw NullPointerException()
            is Cons -> list.tail
        }

        fun <T> getHead(list: List<T>): T = when (list) {
            Nil -> throw NullPointerException()
            is Cons -> list.head
        }

        fun <T> appendTail(list: List<T>, tail: T): List<T> = when (list) {
            Nil -> Cons(tail,
                Nil)
            is Cons -> Cons(
                list.head, appendTail(list.tail, tail))
        }

        fun <T> addHead(list: List<T>, head: T): List<T> = Cons(
            head, list)

        tailrec fun <T> filter(list: List<T>, f: (T) -> Boolean, acc: List<T> = Nil): List<T> = when (list) {
            Nil -> acc
            is Cons -> if (f(list.head)) {
                filter(list.tail, f,
                    appendTail(acc, list.head))
            } else {
                filter(list.tail, f, acc)
            }
        }

        tailrec fun <T> drop(list: List<T>, n: Int): List<T> = when {
            n == 0 || list is Nil -> list
            else -> drop(
                getTail(list), n - 1)
        }

        fun <T> init(list: List<T>): List<T> = when (list) {
            Nil -> Nil
            is Cons -> if (list.tail == Nil) Nil else Cons(
                list.head, init(list.tail))
        }

        tailrec fun <T> take(list: List<T>, n: Int, acc: List<T> = Nil): List<T> = when {
            n == 0 || list is Nil -> acc
            else -> take(
                getTail(list), n - 1,
                appendTail(acc,
                    getHead(list)))
        }

        tailrec fun add2(list: List<Int>, acc: List<Int> = Nil): List<Int> = when (list) {
            Nil -> acc
            is Cons -> add2(
                list.tail, appendTail(acc, list.head + 2))
        }

        tailrec fun product2(list: List<Double>, acc: List<Double> = Nil): List<Double> =
            when (list) {
                Nil -> acc
                is Cons -> product2(
                    list.tail, appendTail(acc, list.head * 2))
            }

        tailrec fun <T, R> map(list: List<T>, f: (T) -> R, acc: List<R> = Nil): List<R> = when (list) {
            Nil -> acc
            is Cons -> map(
                list.tail, f, appendTail(acc, f(list.head)))
        }

        tailrec fun <T, R> foldLeft(list: List<T>, f: (R, T) -> R, acc: R): R = when (list) {
            Nil -> acc
            is Cons -> foldLeft(
                list.tail, f, f(acc, list.head))
        }

        tailrec fun <T, R> zip(list1: List<T>, list2: List<R>, acc: List<Pair<T, R>> = Nil): List<Pair<T, R>> = when {
            list1 is Nil || list2 is Nil -> acc
            else -> zip(
                getTail(list1),
                getTail(list2),
                appendTail(acc,
                    getHead(
                        list1) to getHead(list2)))
        }
    }
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
    this is List.Nil || list is List.Nil -> acc
    else -> List.getTail(this).zip(
        List.getTail(list), acc.appendTail(
        List.getHead(
            this) to List.getHead(list)))
}

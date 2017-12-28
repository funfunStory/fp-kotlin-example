package fp.kotlin.example.chapter05

sealed class List<out T> {

    companion object {

        fun sum(ints: List<Int>): Int = when (ints) {
            Nil -> 0
            is Cons -> ints.head + sum(ints.tail)
        }

        fun product(doubles: List<Double>): Double = when (doubles) {
            Nil -> 1.0
            is Cons -> if (doubles.head == 0.0) 0.0 else doubles.head * product(doubles.tail)
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
            Nil -> Cons(tail, Nil)
            is Cons -> Cons(list.head, appendTail(list.tail, tail))
        }

        fun <T> addHead(list: List<T>, head: T): List<T> = Cons(head, list)

        fun <T> filter(list: List<T>, f: (T) -> Boolean): List<T> = when (list) {
            Nil -> Nil
            is Cons -> if (f(list.head)) {
                Cons(list.head, filter(list.tail, f))
            } else {
                filter(list.tail, f)
            }
        }

        fun <T> drop(list: List<T>, n: Int): List<T> = when {
            n == 0 || list is Nil -> list
            else -> drop(List.getTail(list), n - 1)
        }

        fun <T> take(list: List<T>, n: Int, acc: List<T> = Nil): List<T> = when {
            n == 0 || list is Nil -> acc
            else -> take(List.getTail(list), n - 1, List.appendTail(acc, List.getHead(list)))
        }

        fun <T, R> map(list: List<T>, f: (T) -> R, acc: List<R> = Nil): List<R> = when (list) {
            Nil -> acc
            is Cons -> map(List.getTail(list), f, List.appendTail(acc, f(List.getHead(list))))
        }
    }

    object Nil : List<Nothing>()
    data class Cons<T>(val head: T, var tail: List<T>) : List<T>()
}

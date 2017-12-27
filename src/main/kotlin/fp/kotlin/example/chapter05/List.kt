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
    }

    object Nil : List<Nothing>()
    data class Cons<T>(val head: T, var tail: List<T>) : List<T>()
}

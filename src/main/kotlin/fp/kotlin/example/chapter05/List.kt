package fp.kotlin.example.chapter05

sealed class List<out T> {
    object Nil : List<Nothing>()
    data class Cons<T>(val head: T, var tail: List<T>) : List<T>()
}

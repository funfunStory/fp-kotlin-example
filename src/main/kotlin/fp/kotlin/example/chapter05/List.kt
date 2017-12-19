package fp.kotlin.example.chapter05

sealed class List<out T> {
    object Nil : List<Nothing>()
    data class Cons<out T>(val head: T, val tail: List<T>) : List<T>()
}

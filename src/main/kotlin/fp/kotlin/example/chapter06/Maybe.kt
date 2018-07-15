package fp.kotlin.example.chapter06

sealed class Maybe<T>
object Nothing: Maybe<kotlin.Nothing>()
data class Just<T>(val value: T): Maybe<T>()
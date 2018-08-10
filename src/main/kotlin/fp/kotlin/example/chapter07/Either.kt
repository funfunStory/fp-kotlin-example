package fp.kotlin.example.chapter07

import kotlin.Nothing

fun main(args: Array<String>) {
    println(divideTenByN(5))  // Right(value=2)
    println(divideTenByN(0))  // Left(value=divide by zero)
    println(divideTenByN(5).fmap { r -> r * 2 })  // Right(value=4)
    println(divideTenByN(0).fmap { r -> r * 2 })  // Left(value=divide by zero)
}

private fun divideTenByN(n: Int): Either<String, Int> = try {
    Right(10 / n)
} catch (e: ArithmeticException) {
    Left("divide by zero")
}

sealed class Either<out L, out R> : Functor<R> {

    abstract fun isLeft(): Boolean
    abstract fun isRight(): Boolean

    abstract fun left(): Left<L>
    abstract fun right(): Right<R>

    abstract override fun <R2> fmap(transform: (R) -> R2): Either<L, R2>
}

data class Left<out L>(val value: L): Either<L, Nothing>() {
    override fun isLeft(): Boolean = true
    override fun isRight(): Boolean = false

    override fun left(): Left<L> = this
    override fun right(): Right<Nothing> = throw NoSuchElementException()

    fun get() = value

    override fun <R2> fmap(transform: (kotlin.Nothing) -> R2): Either<L, R2> = this
}

data class Right<out R>(val value: R): Either<Nothing, R>() {
    override fun isLeft(): Boolean = false
    override fun isRight(): Boolean = true

    override fun left(): Left<Nothing> = throw NoSuchElementException()
    override fun right(): Right<R> = this

    fun get() = value

    override fun <R2> fmap(transform: (R) -> R2): Either<Nothing, R2> = Right(transform(value))
}
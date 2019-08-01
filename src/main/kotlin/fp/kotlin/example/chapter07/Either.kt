package fp.kotlin.example.chapter07

import kotlin.Nothing

fun main() {
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
    abstract override fun <R2> fmap(f: (R) -> R2): Either<L, R2>
}

data class Left<out L>(val value: L): Either<L, Nothing>() {
    override fun <R2> fmap(f: (Nothing) -> R2): Either<L, R2> = this
}

data class Right<out R>(val value: R): Either<Nothing, R>() {
    override fun <R2> fmap(f: (R) -> R2): Either<Nothing, R2> = Right(f(value))
}
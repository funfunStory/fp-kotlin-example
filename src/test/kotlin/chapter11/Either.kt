package chapter11

fun main(args: Array<String>) {

    val right: Either<Error, Int> = Either.Right(3)
    val result = eitherOf {
        right.map { it * 10 }
            .map { it / 5 }
    }
}

sealed class Either<out L, out R> {

    abstract fun isLeft(): Boolean
    abstract fun isRight(): Boolean

    abstract fun left(): Left<L>
    abstract fun right(): Right<R>

    data class Left<out L>(val value: L) : Either<L, Nothing>() {
        override fun left() = this
        override fun right(): Nothing = throw NullPointerException()

        override fun isLeft(): Boolean = true
        override fun isRight(): Boolean = false

        fun get() = value
    }

    data class Right<out R>(val value: R) : Either<Nothing, R>() {
        override fun left(): Nothing = throw NullPointerException()
        override fun right() = this

        override fun isLeft(): Boolean = false
        override fun isRight(): Boolean = true

        fun get() = value
    }
}

fun <L> leftOf(value: L): Either<L, Nothing> = Either.Left(value)
fun <R> rightOf(value: R): Either<Nothing, R> = Either.Right(value)

fun <V> eitherOf(action: () -> V): Either<Exception, V> =
    try {
        rightOf(action())
    } catch (e: Exception) {
        leftOf(e)
    }

inline fun <L, R, R2> Either<L, R>.map(f: (R) -> R2): Either<L, R2> =
    when (this) {
        is Either.Left -> this
        is Either.Right -> rightOf(f(value))
    }


inline fun <L, L2, R> Either<L, R>.mapLeft(f: (L) -> L2): Either<L2, R> =
    when (this) {
        is Either.Left -> leftOf(f(value))
        is Either.Right -> this
    }

inline fun <L, R, R2> Either<L, R>.flatMap(f: (R) -> Either.Right<R2>): Either<L, R2> =
    when (this) {
        is Either.Left -> this
        is Either.Right -> f(value)
    }

inline fun <L, L2, R> Either<L, R>.flatMapLeft(f: (L) -> Either.Left<L2>): Either<L2, R> =
    when (this) {
        is Either.Left -> f(value)
        is Either.Right -> this
    }


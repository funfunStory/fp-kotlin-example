package fp.kotlin.example.chapter11.exception

fun main(args: Array<String>) {
    when(val result = divSubTenBy(5)) {
        is Left -> println("divSubTenBy(5) error by ${result.value}")
        is Right -> println("divSubTenBy(5) returns ${result.value}")
    }   // divSubTenBy(5) returns 8

    when(val result = divSubTenBy(0)) {
        is Left -> println("divSubTenBy(0) error by ${result.value}")
        is Right -> println("divSubTenBy(0) returns ${result.value}")
    }   // divSubTenBy(0) error by divide by zero exception
}

private fun divideTenBy(value: Int): Either<String, Int> = try {
    Right(10 / value)
} catch (e: ArithmeticException) {
    Left("divide by zero exception")
}

private fun subtractTenBy(value: Int) = 10 - value

private fun divSubTenBy(value: Int) = divideTenBy(value).fmap { subtractTenBy(it) }
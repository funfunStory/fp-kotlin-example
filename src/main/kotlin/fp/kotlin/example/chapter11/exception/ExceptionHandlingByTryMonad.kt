package fp.kotlin.example.chapter11.exception

fun main() {
    when(val result = divSubTenBy(5)) {
        is Failure -> println("divSubTenBy(5) error by ${result.e}")
        is Success -> println("divSubTenBy(5) returns ${result.value}")
    }   // divSubTenBy(5) returns 5

    when(val result = divSubTenBy(0)) {
        is Failure -> println("divSubTenBy(0) error by ${result.e}")
        is Success -> println("divSubTenBy(0) returns ${result.value}")
    }   // divSubTenBy(0) error by java.lang.ArithmeticException: / by zero
}

private fun divideTenBy(value: Int): Try<Int> = Try.pure(10).fmap { it / value }

private fun subtractTenBy(value: Int) = 10 / value

private fun divSubTenBy(value: Int) = divideTenBy(value).fmap { subtractTenBy(it) }
package fp.kotlin.example.chapter03

fun main() {
    println(even(9999))   // false
    println(odd(9999))    // true
//    println(even(999999)) // java.lang.StackOverflowError occurred
}

private fun even(n: Int): Boolean = when (n) {
    0 -> true
    else -> odd(n -1)
}

private fun odd(n: Int): Boolean = when (n) {
    0 -> false
    else -> even(n - 1)
}
package fp.kotlin.example.chapter03

sealed class Bounce<A>
data class Done<A>(val result: A): Bounce<A>()
data class More<A>(val thunk: () -> Bounce<A>): Bounce<A>()

tailrec fun <A> trampoline(bounce: Bounce<A>): A = when (bounce) {
    is Done -> bounce.result
    is More -> trampoline(bounce.thunk())
}

fun main() {
    println(trampoline(even(999999)))   // false
    println(trampoline(odd(999999)))   // true
}

private fun odd(n: Int): Bounce<Boolean> = when (n) {
    0 -> Done(false)
    else -> More { even(n - 1) }
}

private fun even(n: Int): Bounce<Boolean> = when (n) {
    0 -> Done(true)
    else -> More { odd(n - 1) }
}
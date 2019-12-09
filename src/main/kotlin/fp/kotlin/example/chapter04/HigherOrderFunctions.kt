package fp.kotlin.example.chapter04

fun main() {
    higherOrderFunction1 { println("Hello, Kotlin") }   // Hello, Kotlin

    higherOrderFunction2()()                            // Hello, Kotlin
}

private fun higherOrderFunction1(func: () -> Unit) {
    func()
}

private fun higherOrderFunction2(): () -> Unit {
    return { println("Hello, Kotlin") }
}
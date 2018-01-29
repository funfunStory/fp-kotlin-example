package fp.kotlin.example.chapter04

private fun higherOrderFunction1(func: () -> Unit): Unit {
    func()
}

private fun higherOrderFunction2(): () -> Unit {
    return { println("Hello, Kotlin") }
}
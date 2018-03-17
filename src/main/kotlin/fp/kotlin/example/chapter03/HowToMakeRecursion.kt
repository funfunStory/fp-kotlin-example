package fp.kotlin.example.chapter03

fun main(args: Array<String>) {
    println(func(5))    // 15
}

fun func(n: Int): Int = when {
    n < 0 -> 0
    else -> n + func(n - 1)
}

private fun helloFunc() {
    println("Hello")
    helloFunc()
}

private fun helloFunc(n: Int) {
    when {
        n < 0 -> return
        else -> {
            println("Hello")
            helloFunc(n - 1)
        }
    }
}
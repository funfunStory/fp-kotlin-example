package fp.kotlin.example.chapter03

fun main(args: Array<String>) {
    println(func(5))    // 15
}

fun func(n: Int): Int = when {
    0 >= n -> 0
    else -> n + func(n - 1)
}

private fun helloFunc() {
    println("Hello")
    helloFunc()
}

private fun helloFunc(n: Int) {
    when {
        0 >= n -> return
        else -> {
            println("Hello")
            helloFunc(n - 1)
        }
    }
}
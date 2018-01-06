package fp.kotlin.example.chapter03

private fun fiboDynamic(n: Int, fibo: IntArray): Int {
    fibo[1] = 1
    fibo[2] = 1

    for (i in 0..n) {
        fibo[i + 2] = fibo[i + 1] + fibo[i]
    }

    return fibo[n]
}

private fun fiboRecursion(n: Int): Int {
    return when (n) {
        1 -> 1
        2 -> 1
        else -> fiboRecursion(n - 2) + fiboRecursion(n - 1)
    }
}

fun main(args: Array<String>) {
    println(fiboDynamic(10, IntArray(100)))    // "55" 출력
    println(fiboRecursion(10))    // "55" 출력
}
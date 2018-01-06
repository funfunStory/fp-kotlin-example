package fp.kotlin.example.chapter03

fun main(args: Array<String>) {
    println(fiboRecursion(6))
    println(fiboMemoization(6))
    println(fiboFP(6))
}

private fun fiboFP(n: Int): Int {
    return fiboFP(n, 0, 1)
}

tailrec fun fiboFP(n: Int, first: Int, second: Int): Int {
    return when (n) {
        0 -> first
        1 -> second
        else -> fiboFP(n - 1, second, first + second)
    }
}

private fun fiboRecursion(n: Int): Int {
    println("fiboRecursion($n)")
    return when (n) {
        1 -> 1
        2 -> 1
        else -> fiboRecursion(n - 2) + fiboRecursion(n - 1)
    }
}

private var memo = Array(100, { -1 })

private fun fiboMemoization(n: Int): Int {
    println("fiboMemoization($n)")
    return when {
        1 == n || 2 == n -> 1
        -1 < memo[n] -> memo[n]
        else -> {
            memo[n] = fiboMemoization(n - 2) + fiboMemoization(n - 1)
            return memo[n]
        }
    }
}

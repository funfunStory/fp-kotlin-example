package fp.kotlin.example.chapter03

import fp.kotlin.example.head
import fp.kotlin.example.tail

fun main(args: Array<String>) {
    println(factorial(10))      // "3628800" 출력
    println(maximum(listOf(1, 3, 2, 8, 4)))     // "8" 출력
    println(reverse("abcd"))     // "dcba" 출력
    println(toBinary(10))     // "1010" 출력
    println(replicate(3, 5))    // "[5, 5, 5]" 출력
    println(take(3, listOf(1, 2, 3, 4, 5)))     // "[1, 2, 3]" 출력
    println(elem(5, listOf(1, 3, 5)))   // "true" 출력
    println(zip(listOf(1, 3, 5), listOf(2, 4)))     // "[(1, 2), (3, 4)]" 출력
}

private tailrec fun zip(list1: List<Int>, list2: List<Int>, acc: List<Pair<Int, Int>> = listOf())
    : List<Pair<Int, Int>> = when {
    list1.isEmpty() || list2.isEmpty() -> acc
    else -> {
        val zipList = acc + listOf(Pair(list1.head(), list2.head()))
        zip(list1.tail(), list2.tail(), zipList)
    }
}

private tailrec fun elem(n: Int, list: List<Int>): Boolean = when {
    list.isEmpty() -> false
    n == list.head() -> true
    else -> elem(n, list.tail())
}

private tailrec fun take(n: Int, list: List<Int>, acc: List<Int> = listOf()): List<Int> = when {
    n <= 0 -> acc
    list.isEmpty() -> acc
    else -> {
        val takeList = acc + listOf(list.head())
        take(n - 1, list.tail(), takeList)
    }
}

private tailrec fun replicate(n: Int, element: Int, acc: List<Int> = listOf()): List<Int> = when {
    n <= 0 -> acc
    else -> {
        val repeatList = acc + listOf(element)
        replicate(n - 1, element, repeatList)
    }
}

private tailrec fun toBinary(n: Int, acc: String = ""): String = when {
    2 > n -> n.toString() + acc
    else -> {
        val binary = (n % 2).toString() + acc
        toBinary(n / 2, binary)
    }
}

private tailrec fun reverse(str: String, acc: String = ""): String = when {
    str.isEmpty() -> acc
    else -> {
        val reversed = str.head() + acc
        reverse(str.tail(), reversed)
    }
}

private tailrec fun maximum(items: List<Int>, acc: Int = Int.MIN_VALUE): Int = when {
    items.isEmpty() -> error("empty list")
    items.size == 1 -> acc
    else -> {
        val head = items.head()
        val maxValue = if (head > acc) head else acc
        maximum(items.tail(), maxValue)
    }
}

// 연습문제 3.11
private tailrec fun factorial(n: Int, acc: Int = 1): Int = when (n) {
    0 -> acc
    else -> factorial(n - 1, n * acc)
}

// 출처: https://kotlinlang.org/docs/reference/functions.html
private tailrec fun findFixPoint(x: Double = 1.0): Double = if (x == Math.cos(x)) x else findFixPoint(Math.cos(x))

// 출처: https://kotlinlang.org/docs/reference/functions.html
private fun findFixPoint(): Double {
    var x = 1.0
    while (true) {
        val y = Math.cos(x)
        if (x == y) return x
        x = y
    }
}

private fun tailRecursion(n: Int): Int = when (n) {
    0 -> 0
    else -> tailRecursion(n - 1)
}

private fun headRecursion(n: Int): Int = when {
    n > 0 -> headRecursion(n - 1)
    else -> 0
}



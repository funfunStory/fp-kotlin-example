package fp.kotlin.example.chapter04

import fp.kotlin.example.chapter03.solution.replicate
import fp.kotlin.example.head
import fp.kotlin.example.tail
import kotlin.math.max

fun main(args: Array<String>) {
    val list1 = listOf(6, 3, 2, 1, 4)
    val list2 = listOf(7, 4, 2, 6, 3)

    val add = { p1: Int, p2: Int -> p1 + p2 }
    val result1 = zipWith(add, list1, list2)
    println(result1)    // [13, 7, 4, 7, 7]

    val max = { p1: Int, p2: Int -> max(p1, p2) }
    val result2 = zipWith(max, list1, list2)
    println(result2)    // [7, 4, 2, 6, 4]

    val strcat = { p1: String, p2: String -> p1 + p2 }
    val result3 = zipWith(strcat, listOf("a", "b"), listOf("c", "d"))
    println(result3)    // [ac, bd]

    val product = { p1: Int, p2: Int -> p1 * p2 }
    val result4 = zipWith(product, replicate(3, 5), listOf(1..5).flatten())
    println(result4)    // [5, 10, 15]
}

private tailrec fun <P1, P2, R> zipWith(func: (P1, P2) -> R, list1: List<P1>, list2: List<P2>, acc: List<R> = listOf()): List<R> = when {
    list1.isEmpty() || list2.isEmpty() -> acc
    else -> {
        val zipList = acc + listOf(func(list1.head(), list2.head()))
        zipWith(func, list1.tail(), list2.tail(), zipList)
    }
}
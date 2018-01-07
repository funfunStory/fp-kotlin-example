package fp.kotlin.example.chapter03

import fp.kotlin.example.head
import fp.kotlin.example.tail
import fp.kotlin.example.plus

fun main(args: Array<String>) {
    println(maximum(listOf(1, 3, 2, 8, 4))) // 8
    println(reverse("abcd"))    // dcba
    println(take(3, listOf(1, 2, 3, 4, 5))) // [1, 2, 3]
    println(zip(listOf(1, 3, 5), listOf(2, 4)))     // [(1, 2), (3, 4)]
}

private fun maximum(items: List<Int>): Int = when {
    items.isEmpty() -> error("empty list")
    1 == items.size -> items[0]
    else -> {
        val head = items.head()
        val tail = items.tail()
        val maxVal = maximum(tail)
        if (head > maxVal) head else maxVal
    }
}

private fun reverse(str: String): String = when {
    str.isEmpty() -> ""
    else -> reverse(str.tail()) + str.head()
}

private fun take(n: Int, list: List<Int>): List<Int> = when {
    0 >= n -> listOf()
    list.isEmpty() -> listOf()
    else -> listOf(list.head()) + take(n - 1, list.tail())
}

// use sequence without recursion
// fun repeat(n: Int): Sequence<Int> = generateSequence(n) { it }

// StackOverflowError
//fun repeat(n: Int): Sequence<Int> = sequenceOf(n) + repeat(n)

// use recursion
private fun repeat(n: Int): Sequence<Int> = sequenceOf(n) + { repeat(n) }

private fun zip(list1: List<Int>, list2: List<Int>): List<Pair<Int, Int>> = when {
    list1.isEmpty() -> listOf()
    list2.isEmpty() -> listOf()
    else -> listOf(Pair(list1.head(), list2.head())) + zip(list1.tail(), list2.tail())
}
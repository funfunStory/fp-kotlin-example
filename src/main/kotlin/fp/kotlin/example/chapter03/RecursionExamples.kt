package fp.kotlin.example.chapter03

import fp.kotlin.example.head
import fp.kotlin.example.tail

fun main(args: Array<String>) {
    println(maximum(listOf(1, 3, 2, 8, 4)))
    println(gcd(366, 60))
    println(reverse("abcd"))        // "dcba" 출력
    println(replicate(3, 5))    // "[5, 5, 5]" 출력
    println(take(3, listOf(1, 2, 3, 4, 5)))     // "[1, 2, 3]" 출력
    println(elem(5, listOf(1, 3, 5)))   // "true" 출력
    println(takeSequence(5, repeat(3)))
    println(zip(listOf(1, 3, 5), listOf(2, 4)))     // "[(1, 2), (3, 4)]" 출력
    println(toBinary(10))     // "1010" 출력
    println(quicksort(listOf(5, 3, 7, 6, 2, 1, 4)))     // "[1, 2, 3, 4, 5, 6, 7]" 출력
}

private fun quicksort(list: List<Int>): List<Int> {
    return when {
        list.isEmpty() -> list
        else -> {
            val pivot = list.head()
            val (small, bigger) = list.tail().partition { it < pivot }
            quicksort(small) + listOf(pivot) + quicksort(bigger)
        }
    }
}

private fun toBinary(n: Int): String {
    return when {
        2 > n -> n.toString()
        else -> toBinary(n / 2) + (n % 2).toString()
    }
}

private fun zip(list1: List<Int>, list2: List<Int>): List<Pair<Int, Int>> {
    return when {
        list1.isEmpty() -> listOf()
        list2.isEmpty() -> listOf()
        else -> listOf(Pair(list1.head(), list2.head())) + zip(list1.tail(), list2.tail())
    }
}

operator fun <T> Sequence<T>.plus(other: () -> Sequence<T>) = object : Sequence<T> {
    private val thisIterator: Iterator<T> by lazy { this@plus.iterator() }
    private val otherIterator: Iterator<T> by lazy { other().iterator() }
    override fun iterator() = object : Iterator<T> {
        override fun next(): T =
                if (thisIterator.hasNext())
                    thisIterator.next()
                else
                    otherIterator.next()

        override fun hasNext(): Boolean = thisIterator.hasNext() || otherIterator.hasNext()
    }
}

// TODO: 재귀로 repeat 함수를 구할 방법을 찾아야함. 코틀린은 안됨 ㅜ
private fun repeat(n: Int): Sequence<Int> {
//    return listOf(n).asSequence() + repeat(n)   // StackOverflowError
//    return repeat(n).plus(n)                            // StackOverflowError
//    return listOf(n).asSequence().plus(repeat(n))     // StackOverflowError
//    return generateSequence(n) { it }                 // good
//    return sequenceOf(n) + repeat(n)                // StackOverflowError
    return sequenceOf(n) + { repeat(n) }                // good
}

fun Sequence<Int>.head() = first()
fun Sequence<Int>.tail() = drop(1)

private fun takeSequence(n: Int, sequence: Sequence<Int>): List<Int> {
    return when {
        0 >= n -> listOf()
        sequence.none() -> listOf()
        else -> listOf(sequence.head()) + takeSequence(n - 1, sequence.tail())
    }
}

private fun elem(n: Int, list: List<Int>): Boolean {
    return when {
        list.isEmpty() -> false
        list.head() == n -> true
        else -> elem(n, list.tail())
    }
}

private fun take(n: Int, list: List<Int>): List<Int> {
    return when {
        0 >= n -> listOf()
        list.isEmpty() -> listOf()
        else -> listOf(list.head()) + take(n - 1, list.tail())
    }
}

private fun maximum(items: List<Int>): Int {
    return when {
        items.isEmpty() -> error("empty list")
        1 == items.size -> items[0]
        else -> {
            val head = items.head()
            val tail = items.tail()
            val maxVal = maximum(tail)
            if (head > maxVal) head else maxVal
        }
    }
}

private fun power(x: Double, n: Int): Double {
    return when (n) {
        0 -> 1.0
        else -> x * power(x, n - 1)
    }
}

private fun factorial(n: Int): Int {
    return when (n) {
        0 -> 1
        else -> n * factorial(n - 1)
    }
}

private fun gcd(m: Int, n: Int): Int {
    return when (n) {
        0 -> m
        else -> gcd(n, m % n)
    }
}

fun String.head() = first()
fun String.tail() = drop(1)

private fun reverse(str: String): String {
    return when {
        str.isEmpty() -> ""
        else -> {
            return reverse(str.tail()) + str.head()
        }
    }
}

private fun replicate(n: Int, element: Int): List<Int> {
    return when {
        0 >= n -> listOf()
        else -> listOf(element) + replicate(n - 1, element)
    }
}


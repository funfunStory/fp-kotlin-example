package fp.kotlin.example.chapter03

import fp.kotlin.example.head
import fp.kotlin.example.tail

fun main(args: Array<String>) {
    // [[], [3], [2], [2, 3], [1], [1, 3], [1, 2], [1, 2, 3]]
    println(powerset(setOf(1, 2, 3)))
    // [[], [1], [2], [1, 2], [3], [1, 3], [2, 3], [1, 2, 3]]
    println(powerset(setOf(1, 2, 3), setOf(setOf())))
    // [[], [1], [2], [1, 2], [3], [1, 3], [2, 3], [1, 2, 3]]
    println(setOf(1, 2, 3).powerset())
    // [[], [1], [2], [1, 2], [3], [1, 3], [2, 3], [1, 2, 3]]
    println(listOf(1, 2, 3).powerset())
}

private fun <T> powerset(s: Set<T>): Set<Set<T>> = when {
    s.isEmpty() -> setOf(setOf())
    else -> {
        val head = s.head()
        val restSet = powerset(s.tail())
        restSet + restSet.map { setOf(head) + it }.toSet()
    }
}

private tailrec fun <T> powerset(s: Set<T>, acc:Set<Set<T>>): Set<Set<T>> = when {
    s.isEmpty() -> acc
    else -> powerset(s.tail(), acc + acc.map { it + s.head() })
}

private fun <T> Collection<T>.powerset(): Set<Set<T>> = powerset(this, setOf(setOf()))

private tailrec fun <T> powerset(s: Collection<T>, acc:Set<Set<T>>): Set<Set<T>> = when {
    s.isEmpty() -> acc
    else -> powerset(s.tail(), acc + acc.map { it + s.head() })
}
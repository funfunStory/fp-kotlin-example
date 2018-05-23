package fp.kotlin.example.chapter05

import fp.kotlin.example.chapter05.solution.filter
import fp.kotlin.example.chapter05.solution.map

fun main(args: Array<String>) {
    val bigIntList = (1..10000000).toFunList()
    var start = System.currentTimeMillis()
    funListWay(bigIntList)
    println("${System.currentTimeMillis() - start} ms")    // 9467 ms

    val bigIntStream = (1..10000000).toFunStream()
    start = System.currentTimeMillis()
    funStreamWay(bigIntStream)
    println("${System.currentTimeMillis() - start} ms")    // 7 ms

    start = System.currentTimeMillis()
    bigIntList.forEach { it * it }
    println("${System.currentTimeMillis() - start} ms")    // 75 ms

    start = System.currentTimeMillis()
    bigIntStream.forEach { it * it }
    println("${System.currentTimeMillis() - start} ms")    // 370 ms
}

private fun funListWay(intList: FunList<Int>): Int = intList
    .map { n -> n * n }
    .filter { n -> n < 1000000 }
    .map { n -> n - 2 }
    .filter { n -> n < 1000 }
    .map { n -> n * 10 }
    .getHead()

private fun funStreamWay(intList: FunStream<Int>): Int = intList
    .map { n -> n * n }
    .filter { n -> n < 1000000 }
    .map { n -> n - 2 }
    .filter { n -> n < 1000 }
    .map { n -> n * 10 }
    .getHead()
package fp.kotlin.example.chapter05

fun main(args: Array<String>) {
    val bigIntList = (1..6000).toFunList()
    var start = System.currentTimeMillis()
    bigIntList.foldLeft(0) { acc, elm -> acc + elm }
    println("${System.currentTimeMillis() - start} ms")    // 5 ms

    start = System.currentTimeMillis()
    bigIntList.foldRight(0) { elm, acc -> elm + acc }
    println("${System.currentTimeMillis() - start} ms")    // 2 ms
}
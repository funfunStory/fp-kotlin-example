package fp.kotlin.example.chapter05

fun main(args: Array<String>) {
    val bigIntList = (1..10000000).toFunList()
    var start = System.currentTimeMillis()
    funListWay(bigIntList)
    println("${System.currentTimeMillis() - start} ms")    // 2180 ms

    start = System.currentTimeMillis()
    funStreamWay(bigIntList)
    println("${System.currentTimeMillis() - start} ms")    // 8 ms
}

private fun funListWay(intList: FunList<Int>): Int {
    return intList
            .map { n -> n * n }
            .filter { n -> n < 10 }
            .getHead()
}

private fun funStreamWay(intList: FunList<Int>): Int {
    return intList.toFunStream()
            .map { n -> n * n }
            .filter { n -> n < 10 }
            .getHead()
}
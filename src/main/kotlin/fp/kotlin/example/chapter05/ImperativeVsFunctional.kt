package fp.kotlin.example.chapter05

fun main(args: Array<String>) {
    println(imperativeWay(listOf(1, 2, 3, 4, 5)))   // 1
    println(functionalWay(listOf(1, 2, 3, 4, 5)))    // 1

    val bigIntList = (1..10000000).toList()

    var start = System.currentTimeMillis()
    imperativeWay(bigIntList)
    println("${System.currentTimeMillis() - start} ms")    // 0 ms

    start = System.currentTimeMillis()
    functionalWay(bigIntList)
    println("${System.currentTimeMillis() - start} ms")    // 2349 ms

    start = System.currentTimeMillis()
    realFunctionalWay(bigIntList)
    println("${System.currentTimeMillis() - start} ms")    // 10 ms
}

private fun imperativeWay(intList: List<Int>): Int {
    for (value in intList) {
        val doubleValue = value * value
        if (doubleValue < 10) {
            return doubleValue
        }
    }

    throw NoSuchElementException("There is no value")
}

private fun functionalWay(intList: List<Int>): Int {
    return intList
        .map { n -> n * n }
        .filter { n -> n < 10 }
        .first()
}

private fun realFunctionalWay(intList: List<Int>): Int {
    return intList.asSequence()
        .map { n -> n * n }
        .filter { n -> n < 10 }
        .first()
}
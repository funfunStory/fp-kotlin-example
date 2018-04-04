package fp.kotlin.example.chapter05

fun main(args: Array<String>) {
    println(imperativeFilter(listOf(1, 2, 3, 4, 5, 6, 7, 8)))
    println(functionalFilter(listOf(1, 2, 3, 4, 5, 6, 7, 8)))
}

fun imperativeFilter(numList: List<Int>): List<Int> {
    val newList = mutableListOf<Int>()
    for (num in numList) {
        if (num % 2 == 0) {
            newList.add(num)
        }
    }

    return newList
}

fun functionalFilter(numList: List<Int>): List<Int> {
    return numList.filter { it % 2 == 0 }
}
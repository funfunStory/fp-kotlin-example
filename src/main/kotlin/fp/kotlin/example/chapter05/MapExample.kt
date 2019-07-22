package fp.kotlin.example.chapter05

fun main() {
    println(imperativeMap(listOf(1, 2, 3, 4, 5, 6, 7, 8)))
    println(functionalMap(listOf(1, 2, 3, 4, 5, 6, 7, 8)))
}

fun imperativeMap(numList: List<Int>): List<Int> {
    val newList = mutableListOf<Int>()
    for (num in numList) {
        newList.add(num + 2)
    }

    return newList
}

fun functionalMap(numList: List<Int>): List<Int> {
    return numList.map { it + 2 }
}
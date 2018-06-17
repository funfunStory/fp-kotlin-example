package fp.kotlin.example.chapter11

var z = 0
var y = 0

fun main(args: Array<String>) {
    println(composedInvalidFunctions(listOf(1, 2, 3, 4, 5)))    // [6, 27, 48, 69, 90]
    println(composedInvalidFunctions(listOf(1, 2, 3, 4, 5)))    // [106, 127, 148, 169, 190]
}

fun composedInvalidFunctions(list: List<Int>): List<Int> = list
    .map { invalidAdd10(it) }
    .map { invalidMinus5(it) }

fun invalidAdd10(x: Int): Int {
    val result = x + 10 + z
    z += 10
    return result
}

fun invalidMinus5(x: Int): Int {
    val result = x - 5 + y
    y += 10
    return result
}

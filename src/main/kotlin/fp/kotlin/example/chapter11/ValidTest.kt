package fp.kotlin.example.chapter11

fun main(args: Array<String>) {
    println(composedFunctions(listOf(1, 2, 3, 4, 5)))    // [144, 196, 256, 324, 400]
    println(composedFunctions2(listOf(1, 2, 3, 4, 5)))   // [22, 24, 26, 28, 30]
    println(composedFunctions3(listOf(1, 2, 3, 4, 5)))   // [64, 36, 16, 4, 0]
}

private fun composedFunctions(list: List<Int>): List<Int> = list
    .map { add10(it) }
    .map { minus5(it) }
    .map { twice(it) }
    .filter { isEven(it) }
    .map { square(it) }

private fun composedFunctions2(list: List<Int>): List<Int> = list
    .map { add10(it) }
    .map { twice(it) }
    .filter { isEven(it) }

private fun composedFunctions3(list: List<Int>): List<Int> = list
    .map { minus5(it) }
    .map { twice(it) }
    .filter { isEven(it) }
    .map { square(it) }

private fun add10(x: Int): Int = x + 10

private fun minus5(x: Int): Int = x - 5

private fun twice(x: Int): Int = x * 2

private fun isEven(x: Int): Boolean = x % 2 == 0

private fun square(x: Int): Int = x * x
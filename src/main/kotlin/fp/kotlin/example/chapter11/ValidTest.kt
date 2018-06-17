package fp.kotlin.example.chapter11

fun main(args: Array<String>) {
    println(composedFunctions(listOf(1, 2, 3, 4, 5)))    // [144, 196, 256, 324, 400]
    println(composedFunctions2(listOf(1, 2, 3, 4, 5)))   // [22, 24, 26, 28, 30]
    println(composedFunctions3(listOf(1, 2, 3, 4, 5)))   // [64, 36, 16, 4, 0]
}

fun composedFunctions(list: List<Int>): List<Int> = list
    .map { add10(it) }
    .map { minus5(it) }
    .map { twice(it) }
    .filter { isEven(it) }
    .map { square(it) }

fun composedFunctions2(list: List<Int>): List<Int> = list
    .map { add10(it) }
    .map { twice(it) }
    .filter { isEven(it) }

fun composedFunctions3(list: List<Int>): List<Int> = list
    .map { minus5(it) }
    .map { twice(it) }
    .filter { isEven(it) }
    .map { square(it) }

fun add10(x: Int): Int = x + 10

fun minus5(x: Int): Int = x - 5

fun twice(x: Int): Int = x * 2

fun isEven(x: Int): Boolean = x % 2 == 0

fun square(x: Int): Int = x * x
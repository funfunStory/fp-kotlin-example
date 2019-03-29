package fp.kotlin.example.chapter11.debugging

fun main() {
    val result = listOf(50, 25, 17, 6, 27, 74, 16, 6).asSequence()
            .map { productFive(it) }
            .filter { lessThen100(it) }
            .distinct()
            .sorted()

    result.forEach { println(it) }
}

private fun productFive(x: Int) = x * 5

private fun lessThen100(x: Int) = x < 100


package fp.kotlin.example.chapter01

fun main() {
    val infiniteValue = generateSequence(0) { it + 5 }
    infiniteValue.take(5).forEach { print("$it ") }   // 0 5 10 15 20
}
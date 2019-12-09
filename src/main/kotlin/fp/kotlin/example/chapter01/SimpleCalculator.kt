package fp.kotlin.example.chapter01

fun main() {
    val calculator = SimpleCalculator()

    println(calculator.calculate('+', 3, 1))    // 4
    println(calculator.calculate('-', 3, 1))    // 2
}

class SimpleCalculator {
    fun calculate(operator: Char, num1: Int, num2: Int): Int = when (operator) {
        '+' -> num1 + num2
        '-' -> num1 - num2
        else -> throw IllegalArgumentException()
    }
}
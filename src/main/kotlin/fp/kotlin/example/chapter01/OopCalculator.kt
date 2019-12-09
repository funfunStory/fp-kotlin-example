package fp.kotlin.example.chapter01

fun main() {
    val plusCalculator = OopCalculator(Plus())
    println(plusCalculator.calculate(3, 1))  // 4

    val minusCalculator = OopCalculator(Minus())
    println(minusCalculator.calculate(3, 1))  // 2
}

interface Calculator {
    fun calculate(num1: Int, num2: Int): Int
}

class Plus : Calculator {
    override fun calculate(num1: Int, num2: Int): Int {
        return num1 + num2
    }
}

class Minus : Calculator {
    override fun calculate(num1: Int, num2: Int): Int {
        return num1 - num2
    }
}

class OopCalculator(private val calculator: Calculator) {
    fun calculate(num1: Int, num2: Int): Int {
        if (num1 > num2 && 0 != num2) { // some logic
            return calculator.calculate(num1, num2)
        } else {
            throw IllegalArgumentException()
        }
    }
}
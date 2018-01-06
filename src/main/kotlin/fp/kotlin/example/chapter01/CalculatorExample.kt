package fp.kotlin.example.chapter01

fun main(args: Array<String>) {
    val calculator = SimpleCalculator()

    println(calculator.calculate('+', 3, 1))
    println(calculator.calculate('-', 3, 1))

    val plusCalculator = OopCalculator(Plus())
    println(plusCalculator.calculate(3, 1))  // "4" 출력

    val minusCalculator = OopCalculator(Minus())
    println(minusCalculator.calculate(3, 1))  // "2" 출력

    val fpCalculator = FpCalculator()

    println(fpCalculator.calculate({ n1, n2 -> n1 + n2 }, 3, 1))    // "4" 출력
    println(fpCalculator.calculate({ n1, n2 -> n1 - n2 }, 3, 1))    // "2" 출력
}

class SimpleCalculator {
    fun calculate(operator: Char, num1: Int, num2: Int): Int {
        return when (operator) {
            '+' -> num1 + num2
            '-' -> num1 - num2
            else -> throw IllegalArgumentException()
        }
    }
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

class FpCalculator {
    fun calculate(calculator: (Int, Int) -> Int, num1: Int, num2: Int): Int {
        if (num1 > num2 && 0 != num2) { // some logic
            return calculator(num1, num2)
        } else {
            throw IllegalArgumentException()
        }
    }
}
package fp.kotlin.example.chapter01

fun main() {
    val fpCalculator = FpCalculator()

    println(fpCalculator.calculate({ n1, n2 -> n1 + n2 }, 3, 1))    // 4
    println(fpCalculator.calculate({ n1, n2 -> n1 - n2 }, 3, 1))    // 2
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
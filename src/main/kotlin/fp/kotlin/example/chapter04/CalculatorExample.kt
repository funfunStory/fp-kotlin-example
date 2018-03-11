package fp.kotlin.example.chapter04

fun main(args: Array<String>) {
    // OOP 예제
    val calcSum = Sum()
    val calcMinus = Minus()
    val calcProduct = Product()
    val calcTwiceSum = TwiceSum()

    println(calcSum.calc(1, 5))     // 6
    println(calcMinus.calc(5, 2))   // 3
    println(calcProduct.calc(4, 2)) // 8
    println(calcTwiceSum.calc(8, 2)) //20

    // 고차함수를 사용한 예
    val sum: (Int, Int) -> Int = { x, y -> x + y }
    val product: (Int, Int) -> Int = { x, y -> x * y }
    val minus: (Int, Int) -> Int = { x, y -> x - y }
    val twiceSum: (Int, Int) -> Int = { x, y -> (x + y) * 2 }

    println(higherOrder(sum, 1, 5))     // 6
    println(higherOrder(minus, 5, 2))   // 3
    println(higherOrder(product, 4, 2)) // 8
    println(higherOrder(twiceSum, 8, 2))   // 20
}

interface Calcable {
    fun calc(x: Int, y: Int): Int
}

class Sum : Calcable {
    override fun calc(x: Int, y: Int): Int {
        return x + y
    }
}

class Minus : Calcable {
    override fun calc(x: Int, y: Int): Int {
        return x - y
    }
}

class Product : Calcable {
    override fun calc(x: Int, y: Int): Int {
        return x * y
    }
}

class TwiceSum : Calcable {
    override fun calc(x: Int, y: Int): Int {
        return (x + y) * 2
    }
}

private fun higherOrder(func: (Int, Int) -> Int, x: Int, y: Int): Int = func(x, y)
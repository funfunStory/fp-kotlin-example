package fp.kotlin.example.chapter11

fun main(args: Array<String>) {

    val valueA = 10
    val valueB = 0

    val resultB = try {
        10 / valueB
    } catch (e: Exception) {
        -1
    }

    val resultB2 = if (resultB != -1) {
        resultB + 10
    } else {
        -1
    }

    val resultB3 = if (resultB2 != -1) {
        resultB2 * 2
    } else {
        -1
    }

    println(if (resultB3 != -1) {
        "result is $resultB3"
    } else {
        "result is invalid"
    })

    val listA = listOf("goinhacker", "lazysoul", "kotlin", "fp")

    fun getValidResult(list: List<String>): List<String> {

        // do something

        return list
    }

    fun getInValidResult(list: List<String>): List<String>? {

        // do something

        return null
    }

    val resultC = getValidResult(listA).size
    val resultD = getInValidResult(listA)?.size ?: 0

//    val eitherResult = eitherOf { 10 / valueB }
//        .map { it + 10 }
//        .map { it * 2 }
//
//    println(when (eitherResult) {
//        is Either.Left -> "left is ${eitherResult.left().get()}"
//        is Either.Right -> "right is ${eitherResult.right().get()}"
//    })  // left is java.lang.ArithmeticException: / by zero
}
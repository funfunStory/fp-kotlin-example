package fp.kotlin.example.chapter10.exercise

/**
 *
 * 연습문제 10-10
 *
 * FunStream에 출력함수 printFunStream()를 작성해보자
 *
 * 힌트: [1, 2, 3 ..] 의 형식으로 출력한다.
 */
fun main() {

    val funStream: FunStream<Int> = funStreamOf(1, 2, 3)
    printFunStream(funStream)   // [1, 2, 3]
    require(funStream.toStringByFoldLeft() == "[1, 2, 3]")

    val nilStream = FunStream.Nil
    printFunStream(nilStream)   // []
    require(nilStream.toStringByFoldLeft() == "[]")

}

private fun <T> FunStream<T>.toStringByFoldLeft(): String = TODO()

fun <T> printFunStream(stream: FunStream<T>): Unit = TODO()
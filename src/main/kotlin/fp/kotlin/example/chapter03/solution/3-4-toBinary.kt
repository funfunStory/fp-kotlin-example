package fp.kotlin.example.chapter03.solution

/**
 * 연습문제 3-4
 *
 * 10진수 숫자를 입력받아서 2진수로 문자열로 변환하여 반환하는 함수를 작성하라.
 *
 * 힌트: 함수의 선언 타입은 아래와 같다.
 * fun toBinary(n: Int): String
 */

fun main(args: Array<String>) {
    println(toBinary(10))   // 1010
    println(toBinary(27))   // 11011
    println(toBinary(255))  // 11111111
}

private fun toBinary(n: Int): String = when {
    n < 2 -> n.toString()
    else -> toBinary(n / 2) + (n % 2).toString()
}
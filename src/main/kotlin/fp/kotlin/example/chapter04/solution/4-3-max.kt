package fp.kotlin.example.chapter04.solution

/**
 * 연습문제 4-3
 *
 * 두개의 매개변수를 받아서 큰 값을 반환하는 ``max`` 함수를 커링을 사용할 수 있도록 구현하라.
 */

fun main(args: Array<String>) {
    println(max(10)(30))    // 30
}

private fun max(a: Int) = { b: Int -> if (a >= b) a else b }

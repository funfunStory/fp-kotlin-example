package fp.kotlin.example.chapter03.solution

/**
 * 연습문제 3-5
 *
 * 입력 숫자만큼 입력된 숫자를 가지고 있는 리스트를 반환하는 함수를 만들어보자.
 * 즉, replicate(3, 5)를 입력하면 5가 3개있는 리스트 [5, 5, 5]를 반환한다.
 *
 * 힌트: 함수의 선언 타입은 아래와 같다.
 * fun replicate(n: Int, element: Int): List<Int>
 */

fun main(args: Array<String>) {
    println(replicate(3, 5))    // [5, 5, 5]
    println(replicate(5, 1))    // [1, 1, 1, 1, 1]
}

private fun replicate(n: Int, element: Int): List<Int> = when {
    n <= 0 -> listOf()
    else -> listOf(element) + replicate(n - 1, element)
}
package fp.kotlin.example.chapter03.solution

/**
 * 연습문제 3-15
 *
 * 연습문제 3-5에서 작성한 ``replicate`` 함수가 꼬리 재귀인지 확인해보자. 만약 꼬리 재귀가 아니라면 개선해보자.
 */

fun main(args: Array<String>) {
    println(replicate(3, 5))    // [5, 5, 5]
    println(replicate(5, 1))    // [1, 1, 1, 1, 1]
}

tailrec fun replicate(n: Int, element: Int, acc: List<Int> = listOf()): List<Int> = when {
    0 >= n -> acc
    else -> {
        val repeatList = acc + listOf(element)
        replicate(n - 1, element, repeatList)
    }
}
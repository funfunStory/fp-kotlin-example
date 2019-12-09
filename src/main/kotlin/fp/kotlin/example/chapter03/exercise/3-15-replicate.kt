package fp.kotlin.example.chapter03.exercise

/**
 * 연습문제 3-15
 *
 * 연습문제 3-5에서 작성한 ``replicate`` 함수가 꼬리 재귀인지 확인해보자. 만약 꼬리 재귀가 아니라면 개선해보자.
 */

fun main() {
    require(listOf(5, 5, 5) == replicate(3, 5))
    require(listOf(1, 1, 1, 1, 1) == replicate(5, 1))
}

private tailrec fun replicate(n: Int, element: Int, acc: List<Int> = listOf()): List<Int> = TODO()
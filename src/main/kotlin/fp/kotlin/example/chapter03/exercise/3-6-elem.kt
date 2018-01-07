package fp.kotlin.example.chapter03.exercise

/**
 * 연습문제 3-6
 *
 * 입력값 n이 리스트에 존재하는지 확인하는 함수를 재귀로 작성해보자.
 *
 * 힌트: 함수의 선언 타입은 아래와 같다.
 * fun elem(n: Int, list: List<Int>): Boolean
 */

fun main(args: Array<String>) {
    require(elem(5, listOf(1, 3, 5)))
    require(!elem(5, listOf(1, 3, 7)))
}

private fun elem(n: Int, list: List<Int>): Boolean = TODO()
package fp.kotlin.example.chapter03.exercise

/**
 * 연습문제 3-14
 *
 * 연습문제 3-4에서 작성한 ``toBinary`` 함수가 꼬리 재귀인지 확인해보자. 만약 꼬리 재귀가 아니라면 개선해보자.
 */

fun main() {
    require("1010" == toBinary(10))
    require("11011" == toBinary(27))
    require("11111111" == toBinary(255))
}

private tailrec fun toBinary(n: Int, acc: String = ""): String = TODO()
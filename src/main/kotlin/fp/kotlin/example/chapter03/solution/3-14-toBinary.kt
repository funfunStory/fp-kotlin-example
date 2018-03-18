package fp.kotlin.example.chapter03.solution

/**
 * 연습문제 3-14
 *
 * 연습문제 3-4에서 작성한 ``toBinary`` 함수가 꼬리 재귀인지 확인해보자. 만약 꼬리 재귀가 아니라면 개선해보자.
 */

fun main(args: Array<String>) {
    println(toBinary(10))   // 1010
    println(toBinary(27))   // 11011
    println(toBinary(255))  // 11111111
}

private tailrec fun toBinary(n: Int, acc: String = ""): String = when {
    n < 2 -> n.toString() + acc
    else -> {
        val binary = (n % 2).toString() + acc
        toBinary(n / 2, binary)
    }
}
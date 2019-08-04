package fp.kotlin.example.chapter03.solution

/**
 * 연습문제 3-13
 *
 * 연습문제 3-2에서 작성한 ``power`` 함수가 꼬리 재귀인지 확인해보자. 만약 꼬리 재귀가 아니라면 개선해보자.
 *
 * 힌트: tailrec 어노테이션을 활용하자.
 */

fun main(args: Array<String>) {
    println(power(5.0, 2))   // 25.0
    println(power(2.0, 10))  // 1024.0
}

private tailrec fun power(x: Double, n: Int, acc: Double = 1.0): Double = when (n) {
    0 -> acc
    else -> power(x, n - 1, x * acc)
}
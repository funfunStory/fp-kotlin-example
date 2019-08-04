package fp.kotlin.example.chapter03.solution

/**
 * 연습문제 3-12
 *
 * 연습문제 3-11에서 작성한 ``factorial`` 함수가 꼬리 재귀인지 확인해 보자. 만약 꼬리 재귀가 아니라면 최적화되도록 수정하자.
 *
 * 힌트: tailrec 어노테이션을 활용하자.
 */
fun main(args: Array<String>) {
    println(factorial(1))   // 1
    println(factorial(4))   // 24
    println(factorial(7))   // 5040
    println(factorial(10))  // 3628800
}

private tailrec fun factorial(n: Int, acc: Int = 1): Int = when (n) {
    0 -> acc
    else -> factorial(n - 1, n * acc)
}
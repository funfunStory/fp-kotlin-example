package fp.kotlin.example.chapter03.exercise

/**
 * 연습문제 3-12
 *
 * 연습문제 3-11에서 작성한 ``factorial`` 함수가 꼬리 재귀인지 확인해 보자. 만약 꼬리 재귀가 아니라면 최적화되도록 수정하자.
 *
 * 힌트: tailrec 어노테이션을 활용하자.
 */
fun main() {
    require(1 == factorial(1))
    require(24 == factorial(4))
    require(5040 == factorial(7))
    require(3628800 == factorial(10))
}

private tailrec fun factorial(n: Int, acc: Int = 1): Int = TODO()
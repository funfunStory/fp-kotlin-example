package fp.kotlin.example.chapter04.exercise

/**
 * 연습문제 4-4
 *
 * 연습문제 4-3에서 작성한 ``max`` 함수를 ``curried`` 함수를 만들어서 재작성하라.
 */

fun main(args: Array<String>) {
    /*
     * 주석을 해제하고 아래 조건을 만족하는 함수 max를 구현해보세요.
     */
//    require(30 == max(10)(30))
}

private fun <P1, P2, R> ((P1, P2) -> R).curried(): (P1) -> (P2) -> R = TODO()
package fp.kotlin.example.chapter05.solution

/**
 *
 * 연습문제 5-23
 *
 * 모든 자연수의 제곱근의 합이 1000을 넘으려면 몇개의 자연수가 필요한지 계산하는 함수를 작성해보자.
 *
 * 힌트: 함수는 꼬리재귀로 작성하며, 파라미터는 스스로 정의해서(있을 수도 없을수도..) 문제를 풀어보자.
 *
 */

fun main(args: Array<String>) {
    require(squareRoot() == 14)
}

tailrec fun squareRoot(num: Int = 1, acc: Int = 0): Int = when {
    acc > 1000 -> num - 1
    else -> {
        squareRoot(num + 1, num * num + acc)
    }
}
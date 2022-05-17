package fp.kotlin.example.chapter05.exercise

/**
 *
 * 연습문제 5-24
 *
 * 모든 자연수의 제곱근의 합이 1000을 넘으려면 몇개의 자연수가 필요한지 계산하는 함수를 작성해보자.
 *
 * 힌트: 함수는 꼬리재귀로 작성하자.
 *
 */

fun main() {
    require(squareRoot() == 14)
}

tailrec fun squareRoot(num:Int = 1, acc:Int = 0): Int = TODO()

package fp.kotlin.example.chapter06.solution

/**
 *
 * 연습문제 6-3
 *
 * 연습문제 6-2 에서 작성한 insert 코드를 100000만번 이상 연속해서 insert 해 보자.
 *
 * 힌트 : 테스트하는 디바이스마다 다르겠지만 StackOverflowError 가 날 때까지 해보자.
 *
 */

fun main() {
    (1..100000).fold(EmptyTree as Tree<Int>) { acc, i ->
        acc.insert(i)
    }
}
package fp.kotlin.example.chapter04.exercise

/**
 * 연습문제 4-7
 *
 * 리스트의 값을 조건 함수에 적용했을때, 결괏값이 참인 값의 리스트를 반환하는 ``takeWhile`` 함수를 꼬리재귀로 작성해보자. 예를 들어 입력 리스트가 1, 2,
 * 3, 4, 5로 구성되어 있을 때, 조건 함수가 3보다 작은 값이라면 1과 2를 구성 값으로 가지는 리스트를 반환한다.
 */
fun main() {
    require(listOf(1, 2) == takeWhile({ p -> p < 3 }, listOf(1, 2, 3, 4, 5)))
    require(listOf('h', 'e', 'l', 'l', 'o') == takeWhile({ p -> ' ' != p }, "hello world".toList()))
}

private tailrec fun <P> takeWhile(predicate: (P) -> Boolean, list: List<P>, acc: List<P> = listOf()): List<P> = TODO()
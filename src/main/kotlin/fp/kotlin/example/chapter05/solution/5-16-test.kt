package fp.kotlin.example.chapter05.solution

/**
 *
 * 연습문제 5-16
 *
 * 큰 수에서 감소하는 값들을 가진 리스트를 입력으로 할때, 세가지 함수의 성능을 비교해보자. 그리고 테스트 결과에 대해서 분석해보자.
 *
 * 힌트: 함수의 선언 타입은 아래와 같다.
 *
 */

fun main(args: Array<String>) {
    val bigIntList = (10000000 downTo 1).toList()

    var start = System.currentTimeMillis()
    imperativeWay(bigIntList)
    println("${System.currentTimeMillis() - start} ms")    // 0 ms

    start = System.currentTimeMillis()
    functionalWay(bigIntList)
    println("${System.currentTimeMillis() - start} ms")    // 2366 ms

    start = System.currentTimeMillis()
    realFunctionalWay(bigIntList)
    println("${System.currentTimeMillis() - start} ms")    // 7 ms
}

private fun imperativeWay(intList: List<Int>): Int {
    for (value in intList) {
        val doubleValue = value * value
        if (doubleValue < 10) {
            return doubleValue
        }
    }

    throw NoSuchElementException("There is no value")
}

private fun functionalWay(intList: List<Int>): Int =
    intList
        .map { n -> n * n }
        .filter { n -> n < 10 }
        .first()

private fun realFunctionalWay(intList: List<Int>): Int =
    intList.asSequence()
        .map { n -> n * n }
        .filter { n -> n < 10 }
        .first()
package fp.kotlin.example.chapter04.solution

/**
 * 연습문제 4-9
 *
 * 4-8에서 작성된 ``calculator``의 커링 함수 ``curriedCalculator``를 작성하자.
 */

fun main(args: Array<String>) {
    val curriedWithAdd = curriedCalculator({ x, y -> x + y })
    println(curriedWithAdd(10)(5))                          // 15

    val curriedWithAddWith10 = curriedCalculator({ x, y -> x + y })(10)
    println(curriedWithAddWith10(5))                        // 15

    println(curriedCalculator({ x, y -> x + y })(10)(5))    // 15
}

val curriedCalculator: ((Int, Int) -> Int) -> (Int) -> (Int) -> Int = { calc ->
    { x ->
        { y ->
            calc(x, y)
        }
    }
}

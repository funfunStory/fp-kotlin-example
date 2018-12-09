package fp.kotlin.example.chapter08.exercise

/**
 *
 * 연습문제 8-8
 *
 * 연습문제 8-3 에서 만든 리스트 애플리케이티브 펑터가 준동형 사상 법칙을 만족하는지 확인해보자.
 *
 */

fun main(args: Array<String>) {

    val function: (Int) -> Int = { x: Int -> x * 10 }
    val value: AFunList<Int> = ACons(10, ACons(20, ANil))

    val left = TODO()
    val right = TODO()

    require(left == right)
}
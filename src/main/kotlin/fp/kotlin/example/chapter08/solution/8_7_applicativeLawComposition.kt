package fp.kotlin.example.chapter08.solution

/**
 *
 * 연습문제 8-7
 *
 * 연습문제 8-3 에서 만든 리스트 애플리케이티브 펑터가 합성 법칙을 만족하는지 확인해보자.
 *
 */

fun main(args: Array<String>) {

    val af1: AFunList<(Int) -> Int> = ACons<(Int) -> Int>({ x: Int -> x * 2 }, ANil)
    val af2: AFunList<(Int) -> Int> = ACons<(Int) -> Int>({ x: Int -> x + 10 }, ANil)
    val af3: AFunList<Int> = ACons(1, ACons(2, ANil))

    val leftApply = AFunList.pure(compose<Int, Int, Int>().curried()) apply af1 apply af2 apply af3
    val rightApply = af1 apply (af2 apply af3)

    require(leftApply == rightApply)
}

private fun <P1, P2, P3> compose():((P2)-> P3, (P1)-> P2, P1 ) -> P3  = { f: (P2) -> P3, g: (P1) -> P2, v: P1 -> f(g(v)) }

private fun <P1, P2, P3, R> ((P1, P2, P3) -> R).curried(): (P1) -> (P2) -> (P3) -> R = { p1: P1 ->
    { p2: P2 -> { p3: P3 -> this(p1, p2, p3) } }
}
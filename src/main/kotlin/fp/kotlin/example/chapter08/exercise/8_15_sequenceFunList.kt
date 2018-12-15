package fp.kotlin.example.chapter08.exercise

import fp.kotlin.example.chapter04.solution.curried

/**
 *
 * 연습문제 8-15
 *
 * AFunList 에도 동작하는 ``sequenceA`` 함수를 추가하고 테스트 해보자.
 *
 */
fun main(args: Array<String>) {

    val listOfList = ACons(ACons(1, ACons(2, ACons(3, ANil))), ANil)
    require(sequenceAByFoldRight(listOfList) ==
        ACons(
            ACons(1, ANil),
            ACons(
                ACons(2, ANil),
                ACons(
                    ACons(3, ANil),
                    ANil)
            )
        )
    )
}

private fun <T> cons() = { x: T, xs: AFunList<T> -> ACons(x, xs) }

private fun <T> sequenceAByFoldRight(listOfList: AFunList<AFunList<T>>): AFunList<AFunList<T>> = TODO()
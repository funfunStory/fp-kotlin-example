package fp.kotlin.example.chapter08.exercise

/**
 *
 * 연습문제 8-16
 *
 * AFunList 에도 동작하는 ``sequenceA`` 함수를 추가하고 테스트 해보자.
 *
 */
fun main() {

    val listOfList = ACons(ACons(1, ACons(2, ACons(3, ANil))), ANil)
    require(sequenceA(listOfList) ==
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

private fun <T> sequenceA(listOfList: AFunList<AFunList<T>>): AFunList<AFunList<T>> = TODO()
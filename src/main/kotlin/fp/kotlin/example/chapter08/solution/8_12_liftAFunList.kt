package fp.kotlin.example.chapter08.solution

import fp.kotlin.example.chapter04.solution.curried

/**
 *
 * 연습문제 8-12
 *
 * AFunList 에도 동작하는 ``liftA2`` 함수를 추가해보자.
 *
 */
fun main() {

    val lifted = liftA2 { x: Int, y: Int -> x + y }
    require(lifted(ACons(1, ANil), ACons(2, ANil)) == ACons(3, ANil))

    val lifted2 = liftA2 { x: String, y: String -> x + y }
    require(lifted2(ACons("Hello, ", ANil), ACons("Kotlin", ANil)) == ACons("Hello, Kotlin", ANil))

    val lifted3 = liftA2 { x: Int, y: String -> x.toString() + y }
    require(lifted3(ACons(10, ANil), ACons("Kotlin", ANil)) == ACons("10Kotlin", ANil))

    require(lifted3(ACons(10, ACons(20, ANil)), ACons("Hello, ", ACons("Kotlin", ANil))) ==
        ACons("10Hello, ", ACons("10Kotlin", ACons("20Hello, ", ACons("20Kotlin", ANil)))))

}

private fun <A, B, R> liftA2(binaryFunction: (A, B) -> R): (AFunList<A>, AFunList<B>)-> AFunList<R>  = { f1: AFunList<A>, f2: AFunList<B> ->
    AFunList.pure(binaryFunction.curried()) apply f1 apply f2
}

infix fun <A> AFunList<A>.append(other: AFunList<A>): AFunList<A> = when (this) {
    ANil -> other
    is ACons -> ACons(head, tail append other)
}

infix fun <A, B> AFunList<(A) -> B>.apply(f: AFunList<A>): AFunList<B> = when (this) {
    ANil -> ANil
    is ACons -> f.fmap(head) append (tail apply f)
}
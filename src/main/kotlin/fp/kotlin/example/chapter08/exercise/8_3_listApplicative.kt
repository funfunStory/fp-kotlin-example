package fp.kotlin.example.chapter08.exercise

import fp.kotlin.example.chapter04.solution.curried
import fp.kotlin.example.chapter07.Functor

/**
 *
 * 연습문제 8-3
 *
 * 리스트만들고 펑터를 상속받고, ``pure``와 ``apply ``를 확장 함수로 작성해서 리스트 애플리케이티브 펑터를 만들고 테스트해보자.
 *
 * 힌트 : list 2개를 합치는 확장함수 append 를만들어서 활용해도 된다.
 *       기본코드는 다음과 같다.
 *
 */

sealed class AFunList<out A> : Functor<A> {
    abstract override fun <B> fmap(f: (A) -> B): AFunList<B>

    companion object
}

object ANil : AFunList<Nothing>() {
    override fun <B> fmap(f: (Nothing) -> B): AFunList<B> = ANil
}

data class ACons<A>(val head: A, val tail: AFunList<A>) : AFunList<A>() {
    override fun <B> fmap(f: (A) -> B): AFunList<B> = ACons(f(head), tail.fmap(f))
}

fun <A> AFunList.Companion.pure(value: A): AFunList<A> = TODO()

infix fun <A> AFunList<A>.append(other: AFunList<A>): AFunList<A> = TODO()

infix fun <A, B> AFunList<(A) -> B>.apply(f: AFunList<A>): AFunList<B> = TODO()

fun main(args: Array<String>) {

    val funList: AFunList<(Int) -> Int> = AFunList.pure { x -> x * 3 }
    require(funList apply ACons(1, ACons(2, ACons(3, ACons(4, ANil)))) ==
        ACons(3, ACons(6, ACons(9, ACons(12, ANil)))))

    val funList2: AFunList<(Int) -> Int> =
        ACons({ it * 3 },
            ACons({ it * 10 },
                ACons<(Int) -> Int>({ it - 2 }, ANil)))

    require(funList2 apply ACons(1, ACons(2, ACons(3, ANil))) ==
        ACons(3, ACons(6, ACons(9,
            ACons(10, ACons(20, ACons(30,
                ACons(-1, ACons(0, ACons(1, ANil))))))))))

    val funList3: AFunList<(Int) -> Int> = ANil
    require(funList3 apply ACons(1, ACons(2, ACons(3, ACons(4, ANil)))) == ANil)

    val funList4: AFunList<(Int) -> (Int) -> Int> = AFunList.pure(
        { x: Int, y: Int -> x + y }.curried())
    require(
        funList4
            apply ACons(1, ACons(2, ACons(3, ANil)))
            apply ACons(1, ACons(2, ACons(3, ANil)))
            ==
            ACons(2, ACons(3, ACons(4,
                ACons(3, ACons(4, ACons(5,
                    ACons(4, ACons(5, ACons(6, ANil))))))))))
}


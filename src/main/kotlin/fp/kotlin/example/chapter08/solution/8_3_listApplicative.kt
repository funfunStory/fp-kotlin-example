package fp.kotlin.example.chapter08.solution

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

fun <A, B> AFunList.Companion.pure(value: (A) -> B): AFunList<(A) -> B> = ACons(value, ANil)

infix fun <A> AFunList<A>.append(other: AFunList<A>): AFunList<A> = when (this) {
    ANil -> other
    is ACons -> ACons(head, tail append other)
}

infix fun <A, B> AFunList<(A) -> B>.apply(f: AFunList<A>): AFunList<B> = when (this) {
    ANil -> ANil
    is ACons -> when (f) {
        ANil -> ANil
        is ACons -> f.fmap(head) append (tail apply f)
    }
}

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
}


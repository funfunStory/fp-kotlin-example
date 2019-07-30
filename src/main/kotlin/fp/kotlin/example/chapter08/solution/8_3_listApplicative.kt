package fp.kotlin.example.chapter08.solution

import fp.kotlin.example.chapter04.solution.curried
import fp.kotlin.example.chapter07.Functor

/**
 *
 * 연습문제 8-3
 *
 * 펑터를 상속받은 리스트를 만들고, ``pure``와 ``apply``를 확장 함수로 작성해서 리스트 애플리케이티브 펑터를 만들고 테스트해보자.
 *
 * 힌트 : list 2개를 합치는 확장함수 append를 만들어서 활용하라. ``FunList``의 기본 틀은 아래와 같다.
 */

sealed class FunList<out A> : Functor<A> {
    abstract override fun <B> fmap(f: (A) -> B): FunList<B>

    companion object
}

object Nil : FunList<Nothing>() {
    override fun <B> fmap(f: (Nothing) -> B): FunList<B> = Nil
}

data class Cons<A>(val head: A, val tail: FunList<A>) : FunList<A>() {
    override fun <B> fmap(f: (A) -> B): FunList<B> = Cons(f(head), tail.fmap(f))
}

fun <A> FunList.Companion.pure(value: A): FunList<A> = Cons(value, Nil)

infix fun <A> FunList<A>.append(other: FunList<A>): FunList<A> = when (this) {
    Nil -> other
    is Cons -> Cons(head, tail append other)
}

infix fun <A, B> FunList<(A) -> B>.apply(f: FunList<A>): FunList<B> = when (this) {
    Nil -> Nil
    is Cons -> f.fmap(head) append (tail apply f)
}

fun main() {

    val funList: FunList<(Int) -> Int> = FunList.pure { x -> x * 3 }
    require(funList apply Cons(1, Cons(2, Cons(3, Cons(4, Nil))))
        ==
        Cons(3, Cons(6, Cons(9, Cons(12, Nil)))))

    val funList2: FunList<(Int) -> Int> =
        Cons({ it * 3 },
            Cons({ it * 10 },
                Cons<(Int) -> Int>({ it - 2 }, Nil)))

    require(funList2 apply Cons(1, Cons(2, Cons(3, Nil)))
        ==
        Cons(3, Cons(6, Cons(9,
            Cons(10, Cons(20, Cons(30,
                Cons(-1, Cons(0, Cons(1, Nil))))))))))

    val funList3: FunList<(Int) -> Int> = Nil
    require(funList3 apply Cons(1, Cons(2, Cons(3, Cons(4, Nil)))) == Nil)

    val funList4: FunList<(Int) -> (Int) -> Int> = FunList.pure(
        { x: Int, y: Int -> x + y }.curried())
    require(
        funList4
            apply Cons(1, Cons(2, Cons(3, Nil)))
            apply Cons(1, Cons(2, Cons(3, Nil)))
            ==
            Cons(2, Cons(3, Cons(4,
                Cons(3, Cons(4, Cons(5,
                    Cons(4, Cons(5, Cons(6, Nil))))))))))
}


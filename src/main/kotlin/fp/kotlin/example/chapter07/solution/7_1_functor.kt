package fp.kotlin.example.chapter07.solution

/**
 *
 * 연습문제 7-1
 *
 * 5장에서 만든 FunList를 Functor의 인스턴스로 만들어 보자. FunList에 이미 map 함수 등이 존재하지만, fmap, first, size와 같은 기본적인 기능만
 * 제공하는 형태로 재작성하라.
 *
 * 힌트 : 펑터의 의미에 집중하기 위해 꼬리 재귀나, 효율은 생각하지 않고 작성한다.
 */

sealed class FunList<out A> {
    abstract fun <B> fmap(f: (A) -> B): FunList<B>
    abstract fun first(): A
    abstract fun size(): Int
}

object Nil : FunList<Nothing>() {

    override fun <B> fmap(f: (Nothing) -> B): FunList<B> = Nil

    override fun first(): Nothing = throw NoSuchElementException()

    override fun size(): Int = 0
}

data class Cons<A>(val head: A, val tail: FunList<A>) : FunList<A>() {

    override fun <B> fmap(f: (A) -> B): FunList<B> = Cons(f(head), tail.fmap(f))

    override fun first() = head

    override fun size(): Int = 1 + tail.size()
}

fun main(args: Array<String>) {
    val funList: FunList<Int> = Cons(1, Cons(2, Cons(3, Nil)))

    require(funList.fmap { it * 3 } ==
        Cons(3, Cons(6, Cons(9, Nil))))
    require(funList.first() == 1)
    require(funList.size() == 3)

    val funList2: FunList<Int> = Nil

    require(funList2.fmap { it * 3 } == Nil)
//    require(funList2.first()  throw NoSuchElementException())
    require(funList2.size() == 0)
}
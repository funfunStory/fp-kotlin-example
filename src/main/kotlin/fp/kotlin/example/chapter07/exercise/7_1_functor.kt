package fp.kotlin.example.chapter07.exercise

/**
 * 연습문제 7-1
 *
 * 5장에서 만든 FunList를 Functor의 인스턴스로 만들어 보자.
 * 5장에서 만든 FunList에 이미 map 함수 등이 존재하지만, fmap, isEmpty, size와 같은 기본적인 기능만 제공하는 형태로 재작성하라.
 *
 * 힌트 : 펑터에 집중하기 위해 꼬리 재귀나, 효율은 생각하지 않고 작성한다.
 */

sealed class FunList<out A> {

    abstract fun <B> fmap(f: (A) -> B): FunList<B>
    abstract fun first(): A
    abstract fun isEmpty(): Boolean
    abstract fun size(): Int

    object Nil : FunList<Nothing>() {

        override fun <B> fmap(f: (Nothing) -> B): FunList<B> {
            TODO(
                "not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun first(): Nothing {
            TODO(
                "not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun isEmpty(): Boolean {
            TODO(
                "not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun size(): Int {
            TODO(
                "not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }

    data class Cons<A>(val head: A, val tail: FunList<A>) : FunList<A>() {
        override fun <B> fmap(f: (A) -> B): FunList<B> {
            TODO(
                "not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun first(): Nothing {
            TODO(
                "not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun isEmpty(): Boolean {
            TODO(
                "not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun size(): Int {
            TODO(
                "not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }
}

fun main(args: Array<String>) {
    val funList: FunList<Int> = FunList.Cons(1, FunList.Cons(2, FunList.Cons(3, FunList.Nil)))

    require(funList.fmap { it * 3 } ==
        FunList.Cons(3, FunList.Cons(6, FunList.Cons(9, FunList.Nil))))
    require(funList.first() == 1)
    require(!funList.isEmpty())
    require(funList.size() == 3)

    val funList2: FunList<Int> = FunList.Nil

    require(funList2.fmap { it * 3 } == FunList.Nil)
//    require(funList2.first()  throw NoSuchElementException())
    require(funList2.isEmpty())
    require(funList2.size() == 0)
}
package fp.kotlin.example.chapter10.exercise

/**
 *
 * 연습문제 10-2
 *
 * 연습문제에서 작성한 리스트 모나드의 fmap, pure, apply, leadTo, flatMap 함수가 잘 동작하는지 테스트해보자.
 *
 */

fun main() {
    val funList = Cons(1, Cons(2, Cons(3, Nil)))
    val funlist2 = Cons({ x: Int -> x + 1 }, Cons({ x: Int -> x * 10 } as (Int) -> Int, Nil))

    val fmapResult = funList.fmap { it * 3 }
    val applypResult = funlist2 apply Cons(1, Cons(2, Nil))
    val flatmapResult = funList.flatMap { Cons(it, Cons(it * 2, Cons(it * 3, Nil))) }
    val leadToResult = funList.leadTo(Cons('a', Cons('b', Cons('c', Nil))))

    require(Cons(1, Nil) == FunList.pure(1))
    require(fmapResult == Cons(3, Cons(6, Cons(9, Nil))))
    require(applypResult == Cons(2, Cons(3, Cons(10, Cons(20, Nil)))))
    require(flatmapResult == Cons(1, Cons(2, Cons(3, Cons(2, Cons(4, Cons(6, Cons(3, Cons(6, Cons(9, Nil))))))))))
    require(leadToResult == Cons('a', Cons('b', Cons('c', Cons('a', Cons('b', Cons('c', Cons('a', Cons('b', Cons('c', Nil))))))))))


    val nilList = Nil
    val nilFmapResult = nilList.fmap { x: Int -> x * 3 }
    val nilApplypResult = nilList as FunList<(Int) -> Int> apply Cons(1, Cons(2, Nil))
    val nilFlatmapResult = nilList.flatMap { x: Int -> Cons(x, Cons(x * 2, Cons(x * 3, Nil))) }
    val nilLeadToResult = nilList.leadTo(Cons('a', Cons('b', Cons('c', Nil))))

    require(Nil.pure(1) == Nil)
    require(nilFmapResult == Nil)
    require(nilApplypResult == Nil)
    require(nilFlatmapResult == Nil)
    require(nilLeadToResult == Nil)
}
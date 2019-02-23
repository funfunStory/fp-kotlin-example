package fp.kotlin.example.chapter10.solution

/**
 *
 * 연습문제 10-2
 *
 * 연습문제에서 작성한 리스트 모나드의 fmap, pure, apply, leadTo, flatMap 함수가 잘 동작하는지 테스트해보자.
 *
 */

fun main() {
    val funList = FunList.Cons(1, FunList.Cons(2, FunList.Cons(3, FunList.Nil)))
    val funlist2 = FunList.Cons({ x: Int -> x + 1 }, FunList.Cons({ x: Int -> x * 10 } as (Int) -> Int, FunList.Nil))

    val fmapResult = funList.fmap { it * 3 }
    val applypResult = funlist2 apply FunList.Cons(1, FunList.Cons(2, FunList.Nil))
    val flatmapResult = funList.flatMap { FunList.Cons(it, FunList.Cons(it * 2, FunList.Cons(it * 3, FunList.Nil))) }
    val leadToResult = funList.leadTo(FunList.Cons('a', FunList.Cons('b', FunList.Cons('c', FunList.Nil))))

    require(FunList.Cons(1, FunList.Nil) == FunList.pure(1))
    require(fmapResult == FunList.Cons(3, FunList.Cons(6, FunList.Cons(9, FunList.Nil))))
    require(applypResult == FunList.Cons(2, FunList.Cons(3, FunList.Cons(10, FunList.Cons(20, FunList.Nil)))))
    require(flatmapResult == FunList.Cons(1, FunList.Cons(2, FunList.Cons(3, FunList.Cons(2, FunList.Cons(4, FunList.Cons(6, FunList.Cons(3, FunList.Cons(6, FunList.Cons(9, FunList.Nil))))))))))
    require(leadToResult == FunList.Cons('a', FunList.Cons('b', FunList.Cons('c', FunList.Cons('a', FunList.Cons('b', FunList.Cons('c', FunList.Cons('a', FunList.Cons('b', FunList.Cons('c', FunList.Nil))))))))))


    val nilList = FunList.Nil
    val nilFmapResult = nilList.fmap { x: Int -> x * 3 }
    val nilApplypResult = nilList as FunList<(Int) -> Int> apply FunList.Cons(1, FunList.Cons(2, FunList.Nil))
    val nilFlatmapResult = nilList.flatMap { x: Int -> FunList.Cons(x, FunList.Cons(x * 2, FunList.Cons(x * 3, FunList.Nil))) }
    val nilLeadToResult = nilList.leadTo(FunList.Cons('a', FunList.Cons('b', FunList.Cons('c', FunList.Nil))))

    require(FunList.Nil.pure(1) == FunList.Nil)
    require(nilFmapResult == FunList.Nil)
    require(nilApplypResult == FunList.Nil)
    require(nilFlatmapResult == FunList.Nil)
    require(nilLeadToResult == FunList.Nil)
}
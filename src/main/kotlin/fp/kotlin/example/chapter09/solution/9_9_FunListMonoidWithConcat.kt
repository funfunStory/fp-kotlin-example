package fp.kotlin.example.chapter09.solution

import fp.kotlin.example.chapter05.FunList
import fp.kotlin.example.chapter05.funListOf

/**
 *
 * 연습문제 9-9
 *
 * 리스트 모노이드의 ``mconcat`` 함수를 ``[[1, 2], [3, 4], [5]]``와 같은 중첩 리스트를 넣어서 테스트해 보자. 테스트 결과를 확인하고, 동작 원리를 생각해 보자.
 */
fun main(args: Array<String>) {

    val list1 = funListOf(1, 2)
    val list2 = funListOf(3, 4)


    FunListMonoid<Int>().run{
        require(mappend(list1, list2) == funListOf(1, 2, 3, 4))
        require(mappend(list1, FunList.Nil) == list1)
        require(mappend(FunList.Nil, list2) == list2)
    }
}
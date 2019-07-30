package fp.kotlin.example.chapter08.exercise

/**
 *
 * 연습문제 8-6
 *
 * 연습문제를 통해서 작성한 두 리스트를 ``apply`` 함수로 적용하면 모든 가능한 조합의 리스트가 반환 된다는 것을 확인했다.
 * 이번에는 리스트의 동일한 위치의 함수와 값끼리 적용되는 ZipList 애플리케이티브 펑터를 만들고 테스트해보자.
 * 단, 두 리스트의 길이가 다른 경우, 반환되는 리스트는 둘 중 짧은 리스트의 길이와 같다.
 * 예를들어, ``[(*5), (+10)]``와 ``[10, 20, 30]``을 적용하면 ``[50, 30]``이 반환될 것이다.
 *
 */

fun main() {


    val list1: FunList<(Int) -> Int> = Cons({ x :Int-> x * 5 }, Cons<(Int)-> Int>({ x: Int -> x + 10 } , Nil))
    val list2:  FunList<Int> = Cons(10, Cons(20, Cons(30, Nil)))

    require(list1.zipList(list2) == Cons(50, Cons(30, Nil)))
}

fun <A, B>FunList<(A)->B>.zipList(other: FunList<A>): FunList<B> = TODO()
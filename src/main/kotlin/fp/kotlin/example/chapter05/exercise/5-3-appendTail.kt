import fp.kotlin.example.chapter05.FunList

/**
 *
 * 연습문제 5-3
 *
 * 리스트의 마지막 값을 추가하는 appendTail 함수를 작성해 보자. 이때 불변성을 유지하면서, 원본 리스트를 재활용해야 한다.
 *
 * 힌트: 재귀를 활용해야하고, 함수의 선언 타입은 아래와 같다.
 *
 */

fun main(args: Array<String>) {

    val intList = FunList.Cons(1, FunList.Cons(2, FunList.Cons(3, FunList.Nil)))
    require(intList.appendTail(4) == FunList.Cons(1, FunList.Cons(2, FunList.Cons(3, FunList.Cons(4, FunList.Nil)))))

}

fun <T> FunList<T>.appendTail(value: T): FunList<T> = kotlin.TODO()
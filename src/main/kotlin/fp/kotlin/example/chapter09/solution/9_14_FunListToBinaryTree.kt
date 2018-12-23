package fp.kotlin.example.chapter09.solution

import fp.kotlin.example.chapter05.FunList
import fp.kotlin.example.chapter05.funListOf


/**
 * 연습문제 9-14
 *
 * ``foldMap`` 함수를 활용해서 Tree를 FunList를  변환하는 함수 ``toFunList``를 작성해보자.
 *
 */

fun main(args: Array<String>) {

    val tree = Node(1,
        Cons(Node(2, Cons(Node(3), Cons(Node(4), Nil))),
            Cons(Node(5, Cons(Node(6), Cons(Node(7), Nil))), Nil)))

    val tree2 = Node('a',
        Cons(Node('b', Cons(Node('c'), Cons(Node('d'), Nil))),
            Cons(Node('e', Cons(Node('f'), Cons(Node('g'), Nil))), Nil)))

    require(tree.toFunList() == funListOf(3, 4, 2, 6, 7, 5, 1))
    require(tree2.toFunList() == funListOf('c', 'd', 'b', 'f', 'g', 'e', 'a'))
}

fun <A> Tree<A>.toFunList(): FunList<A> = foldMap({ funListOf(it) }, FunListMonoid())

package fp.kotlin.example.chapter08.exercise

import fp.kotlin.example.chapter08.Node

/**
 *
 * 연습문제 8-17
 *
 * Tree 에도 동작하는 ``sequenceA`` 함수를 추가하고 테스트 해보자.
 *
 */
fun main() {

    val treeList: Cons<Node<Int>> = Cons(Node(1), Cons(Node(2), Cons(Node(3), Nil)))
    require(sequenceA(treeList) == Node(Cons(1, Cons(2, Cons(3, Nil)))))

    val treeList2: Cons<Node<Int>> = Cons(Node(1, listOf(Node(2), Node(3))), Cons(Node(2), Cons(Node(3), Nil)))
    require(sequenceA(treeList2) ==
        Node(Cons(1, Cons(2, Cons(3, Nil))),
            listOf(
                Node(Cons(2, Cons(2, Cons(3, Nil)))),
                Node(Cons(3, Cons(2, Cons(3, Nil))))
            )
        )
    )
}

private fun <T> cons() = { x: T, xs: FunList<T> -> Cons(x, xs) }

private fun <T> sequenceA(treeList: FunList<Node<T>>): Node<FunList<T>> = TODO()

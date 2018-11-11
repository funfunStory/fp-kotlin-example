package fp.kotlin.example.chapter07.solution

import fp.kotlin.example.chapter07.EmptyTree
import fp.kotlin.example.chapter07.Node
import fp.kotlin.example.chapter07.Tree

/**
 *
 * 연습문제 7-4
 *
 * FunList를 Tree로 변환하는 함수인 toTree 함수를 작성하라. 이 함수는 1부터 7까지 값을 포함한 리스트를 그림 7-2와 같은 트리로 만들어준다.
 *
 * 힌트: 연습문제 7-2에서 작성한 insert 함수를 활용하라.
 */

tailrec fun <T> FunList<T>.toTree(acc: Tree<T> = EmptyTree): Tree<T> = when (this) {
    Nil -> acc
    is Cons -> tail.toTree(acc.insert(Node(head, EmptyTree, EmptyTree)))
}

fun main(args: Array<String>) {
    require(Nil.toTree() == EmptyTree)

    require(Cons(1, Nil).toTree()
        ==
        Node(1, EmptyTree, EmptyTree)
    )

    require(Cons(1, Cons(2, Nil)).toTree()
        ==
        Node(1,
            Node(2,
                EmptyTree,
                EmptyTree), EmptyTree)
    )

    require(Cons(1, Cons(2, Cons(3, Nil))).toTree()
        ==
        Node(1,
            Node(2, EmptyTree, EmptyTree),
            Node(3, EmptyTree, EmptyTree))
    )

    require(Cons(1, Cons(2, Cons(3, Cons(4, Nil)))).toTree()
        ==
        Node(1,
            Node(2,
                Node(4, EmptyTree, EmptyTree),
                EmptyTree),
            Node(3, EmptyTree, EmptyTree))
    )

    require(Cons(1, Cons(2, Cons(3, Cons(4, Cons(5, Nil))))).toTree() ==
        Node(1,
            Node(2,
                Node(4, EmptyTree, EmptyTree),
                Node(5, EmptyTree, EmptyTree)),
            Node(3, EmptyTree, EmptyTree))
    )

    require(Cons(1, Cons(2, Cons(3, Cons(4, Cons(5, Cons(6, Nil)))))).toTree()
        ==
        Node(1,
            Node(2,
                Node(4,
                    Node(6, EmptyTree, EmptyTree),
                    EmptyTree),
                Node(5, EmptyTree, EmptyTree)),
            Node(3, EmptyTree, EmptyTree))
    )

    require(Cons(1, Cons(2, Cons(3, Cons(4, Cons(5, Cons(6, Cons(7, Nil))))))).toTree()
        ==
        Node(1,
            Node(2,
                Node(4,
                    Node(6, EmptyTree, EmptyTree),
                    Node(7, EmptyTree, EmptyTree)),
                Node(5, EmptyTree, EmptyTree)),
            Node(3, EmptyTree, EmptyTree))
    )
}
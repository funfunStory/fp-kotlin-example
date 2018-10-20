package fp.kotlin.example.chapter07.solution

import fp.kotlin.example.chapter07.EmptyTree
import fp.kotlin.example.chapter07.Tree
import fp.kotlin.example.chapter07.treeOf

/**
 * FunList를 Tree로 변환하는 함수인 toTree 함수를 작성하라. 이 함수는 1부터 7까지 값을 포함한 리스트를 그림 7-2와 같은 트리로 만들어준다.
 *
 * 힌트: 연습문제 7-2에서 작성한 insert 함수를 활용하라.
 */

tailrec fun <T> FunList<T>.toTree(acc: Tree<T> = EmptyTree): Tree<T> = when (this) {
    Nil -> acc
    is Cons -> tail.toTree(acc.insert(treeOf(head)))
}

fun main(args: Array<String>) {
    require(Nil.toTree() == EmptyTree)

    require(Cons(1, Nil).toTree()
            ==
            treeOf(1)
    )

    require(Cons(1, Cons(2, Nil)).toTree()
            ==
            treeOf(1, treeOf(2))
    )

    require(Cons(1, Cons(2, Cons(3, Nil))).toTree()
            ==
            treeOf(1, treeOf(2), treeOf(3))
    )

    require(Cons(1, Cons(2, Cons(3, Cons(4, Nil)))).toTree()
            ==
            treeOf(1, treeOf(2, treeOf(4)), treeOf(3))
    )

    require(Cons(1, Cons(2, Cons(3, Cons(4, Cons(5, Nil))))).toTree() ==
            treeOf(1, treeOf(2, treeOf(4), treeOf(5)), treeOf(3))
    )

    require(Cons(1, Cons(2, Cons(3, Cons(4, Cons(5, Cons(6, Nil)))))).toTree()
            ==
            treeOf(1, treeOf(2, treeOf(4, treeOf(6)), treeOf(5)), treeOf(3))
    )

    require(Cons(1, Cons(2, Cons(3, Cons(4, Cons(5, Cons(6, Cons(7, Nil))))))).toTree()
            ==
            treeOf(1, treeOf(2, treeOf(4, treeOf(6), treeOf(7)), treeOf(5)), treeOf(3))
    )
}
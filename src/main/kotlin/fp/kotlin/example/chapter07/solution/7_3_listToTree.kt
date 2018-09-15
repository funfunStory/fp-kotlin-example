package fp.kotlin.example.chapter07.solution

import fp.kotlin.example.chapter07.EmptyTree
import fp.kotlin.example.chapter07.Tree
import fp.kotlin.example.chapter07.treeOf

/**
 * FunList를 트리로 변환하는 함수인 toTree 함수를 작성하라.
 * 이 함수는 [1,2,3,4,5,6,7]인 리스트를 그림 7-2와 같은 트리로 만들어준다.
 *
 * 힌트: 연습문제 7-2에서 작성한 insert 함수를 활용하라.
 */

tailrec fun <T> FunList<T>.toTree(acc: Tree<T> = EmptyTree): Tree<T> = when (this) {
    FunList.Nil -> acc
    is FunList.Cons -> tail.toTree(acc.insert(treeOf(head)))
}

fun main(args: Array<String>) {
    require(FunList.Nil.toTree() == EmptyTree)

    require(FunList.Cons(1, FunList.Nil).toTree() == treeOf(1))

    require(FunList.Cons(1,
        FunList.Cons(2, FunList.Nil)).toTree() ==
        treeOf(1,
            treeOf(2)))

    require(FunList.Cons(1,
        FunList.Cons(2,
            FunList.Cons(3, FunList.Nil))).toTree() ==
        treeOf(1,
            treeOf(2),
            treeOf(3)))

    require(FunList.Cons(1,
        FunList.Cons(2,
            FunList.Cons(3,
                FunList.Cons(4, FunList.Nil)))).toTree() ==
        treeOf(1,
            treeOf(2,
                treeOf(4)),
            treeOf(3)))

    require(FunList.Cons(1,
        FunList.Cons(2,
            FunList.Cons(3,
                FunList.Cons(4,
                    FunList.Cons(5, FunList.Nil))))).toTree() ==
        treeOf(1,
            treeOf(2,
                treeOf(4),
                treeOf(5)),
            treeOf(3)))

    require(FunList.Cons(1,
        FunList.Cons(2,
            FunList.Cons(3,
                FunList.Cons(4,
                    FunList.Cons(5,
                        FunList.Cons(6, FunList.Nil)))))).toTree() ==
        treeOf(1,
            treeOf(2,
                treeOf(4
                    , treeOf(6)),
                treeOf(5)),
            treeOf(3)))

    require(FunList.Cons(1,
        FunList.Cons(2,
            FunList.Cons(3,
                FunList.Cons(4,
                    FunList.Cons(5,
                        FunList.Cons(6,
                            FunList.Cons(7, FunList.Nil))))))).toTree() ==
        treeOf(1,
            treeOf(2,
                treeOf(4,
                    treeOf(6), treeOf(7)),
                treeOf(5)),
            treeOf(3)))
}
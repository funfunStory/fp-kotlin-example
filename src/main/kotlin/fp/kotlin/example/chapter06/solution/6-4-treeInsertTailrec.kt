package fp.kotlin.example.chapter06.solution

import fp.kotlin.example.chapter05.FunStream
import fp.kotlin.example.chapter05.addHead
import fp.kotlin.example.chapter05.funStreamOf

/**
 *
 * 연습문제 6-4
 *
 * SOF가 일어나지 않도록 insertTailrec을 작성해보자.
 *
 * 힌트 : 함수의 선언 타입은 아래와 같다.
 *       필요하다면 내부 함수를 별도로 생성하자.
 *
 */

fun main() {
    val tree1 = EmptyTree.insertTailrec(5)
    require(tree1 == Node(5, EmptyTree, EmptyTree))

    val tree2 = tree1.insertTailrec(3)
    require(tree2 ==
        Node(5,
            Node(3, EmptyTree, EmptyTree),
            EmptyTree)
    )

    val tree3 = tree2.insertTailrec(10)
    require(tree3 ==
        Node(5,
            Node(3, EmptyTree, EmptyTree),
            Node(10, EmptyTree, EmptyTree)
        )
    )

    val tree4 = tree3.insertTailrec(20)
    require(tree4 ==
        Node(5,
            Node(3, EmptyTree, EmptyTree),
            Node(10,
                EmptyTree,
                Node(20, EmptyTree, EmptyTree)
            )
        )
    )

    val tree5 = tree4.insertTailrec(4)
    require(tree5 ==
        Node(5,
            Node(3,
                EmptyTree,
                Node(4, EmptyTree, EmptyTree)),
            Node(10,
                EmptyTree,
                Node(20, EmptyTree, EmptyTree)
            )
        )
    )

    val tree6 = tree5.insertTailrec(2)
    require(tree6 ==
        Node(5,
            Node(3,
                Node(2, EmptyTree, EmptyTree),
                Node(4, EmptyTree, EmptyTree)
            ),
            Node(10,
                EmptyTree,
                Node(20, EmptyTree, EmptyTree)
            )
        )
    )

    val tree7 = tree6.insertTailrec(8)
    require(tree7 ==
        Node(5,
            Node(3,
                Node(2, EmptyTree, EmptyTree),
                Node(4, EmptyTree, EmptyTree)
            ),
            Node(10,
                Node(8, EmptyTree, EmptyTree),
                Node(20, EmptyTree, EmptyTree)
            )
        )
    )

    (1..100000).fold(EmptyTree as Tree<Int>) { acc, i ->
        acc.insertTailrec(i)
    }

}

fun Tree<Int>.insertTailrec(elem: Int): Tree<Int> = rebuild(path(this, elem), elem)

private fun path(tree: Tree<Int>, value: Int): FunStream<Pair<Tree<Int>, Boolean>> {

    tailrec fun loop(tree: Tree<Int>,
        path: FunStream<Pair<Tree<Int>, Boolean>>): FunStream<Pair<Tree<Int>, Boolean>> =
        when (tree) {
            EmptyTree -> path
            is Node -> when {
                value < tree.value -> loop(tree.leftTree, path.addHead(tree to false))
                else -> loop(tree.rightTree, path.addHead(tree to true))
            }
        }

    return loop(tree, funStreamOf())
}

private fun rebuild(path: FunStream<Pair<Tree<Int>, Boolean>>, value: Int): Tree<Int> {

    tailrec fun loop(path: FunStream<Pair<Tree<Int>, Boolean>>, subTree: Tree<Int>): Tree<Int> =
        when (path) {
            FunStream.Nil -> subTree
            is FunStream.Cons -> when ((path.head()).second) {
                false -> loop(path.tail(),
                    Node((path.head().first as Node).value, subTree,
                        (path.head().first as Node).rightTree))
                true -> loop(path.tail(), Node((path.head().first as Node).value,
                    (path.head().first as Node).leftTree, subTree))

            }
        }
    return loop(path, Node(value, EmptyTree, EmptyTree))
}
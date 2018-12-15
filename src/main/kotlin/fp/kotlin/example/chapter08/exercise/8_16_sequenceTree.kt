package fp.kotlin.example.chapter08.exercise

import fp.kotlin.example.chapter04.solution.curried
import fp.kotlin.example.chapter08.Node
import fp.kotlin.example.chapter08.Tree
import fp.kotlin.example.chapter08.apply
import fp.kotlin.example.chapter08.pure

/**
 *
 * 연습문제 8-16
 *
 * Tree 에도 동작하는 ``sequenceA`` 함수를 추가하고 테스트 해보자.
 *
 */
fun main(args: Array<String>) {

    val treeList: ACons<Node<Int>> = ACons(Node(1), ACons(Node(2), ACons(Node(3), ANil)))
    require(sequenceAByFoldRight(treeList) == Node(ACons(1, ACons(2, ACons(3, ANil)))))

    val treeList2: ACons<Node<Int>> = ACons(Node(1, listOf(Node(2), Node(3))), ACons(Node(2), ACons(Node(3), ANil)))
    require(sequenceAByFoldRight(treeList2) ==
        Node(ACons(1, ACons(2, ACons(3, ANil))),
            listOf(
                Node(ACons(2, ACons(2, ACons(3, ANil)))),
                Node(ACons(3, ACons(2, ACons(3, ANil))))
            )
        )
    )
}

private fun <T> cons() = { x: T, xs: AFunList<T> -> ACons(x, xs) }

private fun <T> sequenceAByFoldRight(treeList: AFunList<Node<T>>): Node<AFunList<T>> = TODO()

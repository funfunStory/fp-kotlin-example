package fp.kotlin.example.chapter08.solution

import fp.kotlin.example.chapter04.solution.curried
import fp.kotlin.example.chapter08.Node
import fp.kotlin.example.chapter08.Tree
import fp.kotlin.example.chapter08.apply
import fp.kotlin.example.chapter08.pure

/**
 *
 * 연습문제 8-5
 *
 * 아래 두 트리를 apply로 결합한 트리를 프로그램으로 만들어보고, 결과가 맞는지 확인해보자.
 * Node(1, listOf(Node(2, listOf(Node(3))), Node(4, listOf())))
 * Node(5, listOf(Node(6), Node(7, listOf(Node(8), Node(9)))))
 */

fun main(args: Array<String>) {

    val tree: Tree<Int> =
        Node(5, listOf(
            Node(6),
            Node(7, listOf(
                Node(8),
                Node(9))),
            Node(10, listOf(
                Node(12),
                Node(14, listOf(
                    Node(16),
                    Node(18))),
                Node(15, listOf(
                    Node(18),
                    Node(21, listOf(
                        Node(24),
                        Node(27))))))),
            Node(20, listOf(
                Node(24),
                Node(28, listOf(
                    Node(32),
                    Node(36))))))
        )

    require(tree == Tree.pure({ x: Int, y: Int -> x * y }.curried())
        apply Node(1, listOf(Node(2, listOf(Node(3))), Node(4, listOf())))
        apply Node(5, listOf(Node(6), Node(7, listOf(Node(8), Node(9)))))
    )
}
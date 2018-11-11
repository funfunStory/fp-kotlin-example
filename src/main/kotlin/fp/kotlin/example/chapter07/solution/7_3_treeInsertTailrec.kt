package fp.kotlin.example.chapter07.solution

import fp.kotlin.example.chapter07.EmptyTree
import fp.kotlin.example.chapter07.Node
import fp.kotlin.example.chapter07.Tree

/**
 *
 * 연습문제 7-3
 *
 * 7-2에서 작성한 insert 함수를 꼬리 재귀로 작성하자.
 *
 * 힌트 : 함수의 선언 타입은 아래와 같다.
 *       7-2 에서 작성한 insert 함수를 내부에서 사용하자.
 */

tailrec fun <A> Tree<A>.insertTailrec(tree: Tree<A>, acc: Tree<A> = EmptyTree): Tree<A> = when (this) {
    EmptyTree -> acc.insert(tree)
    is Node -> when (leftTree) {
        EmptyTree -> acc.insert(Node(value, tree, rightTree))
        is Node -> when (rightTree) {
            EmptyTree -> acc.insert(Node(value, leftTree, tree))
            is Node -> leftTree.insertTailrec(tree, acc.insert(Node(value, EmptyTree, rightTree)))
        }
    }
}

fun main(args: Array<String>) {
    val emptyTree = EmptyTree

    val tree1 = emptyTree.insertTailrec(Node(1, EmptyTree, EmptyTree))
    require(tree1 == Node(1, EmptyTree, EmptyTree))

    val tree2 = tree1.insertTailrec(Node(2, EmptyTree, EmptyTree))
    require(tree2 ==
        Node(1,
            Node(2, EmptyTree, EmptyTree), EmptyTree))

    val tree3 = tree2.insertTailrec(Node(3, EmptyTree, EmptyTree))
    require(tree3 ==
        Node(1,
            Node(2, EmptyTree, EmptyTree), Node(3, EmptyTree, EmptyTree)))

    val tree4 = tree3.insertTailrec(Node(4, EmptyTree, EmptyTree))
    require(tree4 ==
        Node(1,
            Node(2,
                Node(4, EmptyTree, EmptyTree), EmptyTree),
            Node(3, EmptyTree, EmptyTree)))

    val tree5 = tree4.insertTailrec(Node(5, EmptyTree, EmptyTree))
    require(tree5 ==
        Node(1,
            Node(2,
                Node(4, EmptyTree, EmptyTree), Node(5, EmptyTree, EmptyTree)),
            Node(3, EmptyTree, EmptyTree)))

    val tree6 = tree5.insertTailrec(Node(6, EmptyTree, EmptyTree))
    require(tree6 ==
        Node(1,
            Node(2,
                Node(4,
                    Node(6, EmptyTree, EmptyTree), EmptyTree),
                Node(5, EmptyTree, EmptyTree)),
            Node(3, EmptyTree, EmptyTree)))

    val tree7 = tree6.insertTailrec(Node(7, EmptyTree, EmptyTree))
    require(tree7 == Node(1,
        Node(2,
            Node(4,
                Node(6, EmptyTree, EmptyTree), Node(7, EmptyTree, EmptyTree)),
            Node(5, EmptyTree, EmptyTree)),
        Node(3, EmptyTree, EmptyTree)))

}
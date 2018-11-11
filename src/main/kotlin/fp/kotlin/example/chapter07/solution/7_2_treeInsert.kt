package fp.kotlin.example.chapter07.solution

import fp.kotlin.example.chapter07.EmptyTree
import fp.kotlin.example.chapter07.Node
import fp.kotlin.example.chapter07.Tree
import fp.kotlin.example.chapter07.treeOf

/**
 * 4장에서 작성한 Tree에 새로운 노드를 삽입하는 insert 함수를 확장 함수로 추가하라. 균형 트리(balanced tree)는 고려하지 않고, insert 함수는 트리의
 * 왼쪽 하위 부터 비어있는 노드에 하나씩 추가된다. 하위 노드가 없다면 왼쪽에 노드를 추가하고, 왼쪽에 노드가 있다면 오른쪽에 노드를 추가한다. 예를들어 1부터
 * 7까지 차례로 넣는다면 아래 그림 7-2와 같은 트리가 생성된다.
 *
 * 힌트: Tree의 생성자 패턴매칭을 이용한 재귀를 활용할 수 있다.
 */

fun <A> Tree<A>.insert(tree: Tree<A>): Tree<A> = when (this) {
    EmptyTree -> tree
    is Node -> when (leftTree) {
        EmptyTree -> treeOf(value, tree, rightTree)
        is Node -> when (rightTree) {
            EmptyTree -> treeOf(value, leftTree, tree)
            is Node -> treeOf(value, leftTree.insert(tree), rightTree)
        }
    }
}

fun main(args: Array<String>) {
    val emptyTree = EmptyTree

    val tree1 = emptyTree.insert(Node(1, EmptyTree, EmptyTree))
    require(tree1 == Node(1, EmptyTree, EmptyTree))

    val tree2 = tree1.insert(Node(2, EmptyTree, EmptyTree))
    require(tree2 ==
        Node(1,
            Node(2, EmptyTree, EmptyTree), EmptyTree))

    val tree3 = tree2.insert(Node(3, EmptyTree, EmptyTree))
    require(tree3 ==
        Node(1,
            Node(2, EmptyTree, EmptyTree), Node(3, EmptyTree, EmptyTree)))

    val tree4 = tree3.insert(Node(4, EmptyTree, EmptyTree))
    require(tree4 ==
        Node(1,
            Node(2,
                Node(4, EmptyTree, EmptyTree), EmptyTree),
            Node(3, EmptyTree, EmptyTree)))

    val tree5 = tree4.insert(Node(5, EmptyTree, EmptyTree))
    require(tree5 ==
        Node(1,
            Node(2,
                Node(4, EmptyTree, EmptyTree), Node(5, EmptyTree, EmptyTree)),
            Node(3, EmptyTree, EmptyTree)))

    val tree6 = tree5.insert(Node(6, EmptyTree, EmptyTree))
    require(tree6 ==
        Node(1,
            Node(2,
                Node(4,
                    Node(6, EmptyTree, EmptyTree), EmptyTree),
                Node(5, EmptyTree, EmptyTree)),
            Node(3, EmptyTree, EmptyTree)))

    val tree7 = tree6.insert(Node(7, EmptyTree, EmptyTree))
    require(tree7 == Node(1,
        Node(2,
            Node(4,
                Node(6, EmptyTree, EmptyTree), Node(7, EmptyTree, EmptyTree)),
            Node(5, EmptyTree, EmptyTree)),
        Node(3, EmptyTree, EmptyTree)))

}
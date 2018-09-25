package fp.kotlin.example.chapter07.solution

import fp.kotlin.example.chapter07.EmptyTree
import fp.kotlin.example.chapter07.Node
import fp.kotlin.example.chapter07.Tree
import fp.kotlin.example.chapter07.treeOf

/**
 * Tree에 새로운 노드를 삽입하는 insert 함수를 추가하자.
 * insert 함수는 트리의 왼쪽 하위 부터 비어있는 노드에 하나씩 추가된다.
 * (왼쪽 노드가 비어 있다면 왼쪽노드에 insert, 오른쪽 노드가 비어 있다면 오른쪽 노드에 insert, 왼쪽 오른쪽 모두 비어 있지 않다면, 왼쪽노드를 다시 탐색 한다.)
 *
 * 힌트 :
 * Tree의 생성자 패턴매칭을 이용한 재귀로 구현하라. 그리고 Tree안에 함수를 추가하지 않고, 확장 함수로 작성하라.
 * 밸런스 트리는 고려하지 않는다. 순차적으로 (1, 2, 3, 4, 5, 6, 7)을 차례로 인서트 한다면  아래 그림 처럼 트리가 작성된다.(책 참조)
 */

fun <A> Tree<out A>.insert(tree: Tree<A>): Tree<A> = when (this) {
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

    val tree1 = emptyTree.insert(treeOf(1))
    require(tree1 == treeOf(1))

    val tree2 = tree1.insert(treeOf(2))
    require(tree2 == treeOf(1, treeOf(2)))

    val tree3 = tree2.insert(treeOf(3))
    require(tree3 == treeOf(1, treeOf(2), treeOf(3)))

    val tree4 = tree3.insert(treeOf(4))
    require(tree4 == treeOf(1, treeOf(2, treeOf(4)), treeOf(3)))

    val tree5 = tree4.insert(treeOf(5))
    require(tree5 == treeOf(1, treeOf(2, treeOf(4), treeOf(5)), treeOf(3)))

    val tree6 = tree5.insert(treeOf(6))
    require(tree6 == treeOf(1, treeOf(2, treeOf(4, treeOf(6)), treeOf(5)), treeOf(3)))

    val tree7 = tree6.insert(treeOf(7))
    require(tree7 == treeOf(1, treeOf(2, treeOf(4, treeOf(6), treeOf(7)), treeOf(5)), treeOf(3)))

}
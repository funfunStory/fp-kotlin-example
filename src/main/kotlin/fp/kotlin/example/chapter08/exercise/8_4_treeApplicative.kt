package fp.kotlin.example.chapter08.exercise

import fp.kotlin.example.chapter04.solution.curried
import fp.kotlin.example.chapter08.Node
import fp.kotlin.example.chapter08.Tree
import fp.kotlin.example.chapter08.apply
import fp.kotlin.example.chapter08.pure

/**
 *
 * 연습문제 8-4
 *
 * 아래와 같이 두 트리를 반대의 순서로 적용했을때는 어떤 트리가 완성될지 예상해보고, 테스트해보자.
 *
 */

fun main() {

    val tree: Tree<Int> = TODO()

    require(tree == Tree.pure({ x: Int, y: Int -> x * y }.curried())
        apply Node(4, listOf(Node(5), Node(6)))
        apply Node(1, listOf(Node(2), Node(3)))
    )
}


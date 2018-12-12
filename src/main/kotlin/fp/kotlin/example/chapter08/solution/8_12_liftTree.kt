package fp.kotlin.example.chapter08.solution

import fp.kotlin.example.chapter04.solution.curried
import fp.kotlin.example.chapter08.Node
import fp.kotlin.example.chapter08.Tree
import fp.kotlin.example.chapter08.apply
import fp.kotlin.example.chapter08.pure

/**
 *
 * 연습문제 8-12
 *
 * Tree 에도 동작하는 ``liftA2`` 함수를 추가해보자.
 *
 */
fun main(args: Array<String>) {

    val lifted = liftA2 { x: Int, y: Int -> x + y }
    require(lifted(Node(1), Node(2)) == Node(3))

    val lifted2 = liftA2 { x: String, y: String -> x + y }
    require(lifted2(Node("Hello, "), Node("Kotlin")) == Node("Hello, Kotlin"))

    val lifted3 = liftA2 { x: Int, y: String -> x.toString() + y }
    require(lifted3(Node(10), Node("Kotlin")) == Node("10Kotlin"))
}

private fun <A, B, R> liftA2(binaryFunction: (A, B) -> R): (Node<A>, Node<B>)-> Node<R> = { f1: Node<A>, f2: Node<B> ->
    Tree.pure(binaryFunction.curried()) apply f1 apply f2
}
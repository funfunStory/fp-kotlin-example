package fp.kotlin.example.chapter08

import fp.kotlin.example.chapter07.Functor

fun main(args: Array<String>) {
    val tree = Node(1, listOf(Node(2), Node(3)))

    println(tree.fmap { it * 2 })   // 2 [4 [], 6 []]
    println(Tree.pure({ x: Int -> x * 2 }) apply tree)  // 2 [4 [], 6 []]

    println(Tree.pure({ x: Int, y: Int -> x * y }.curried())
            apply Node(1, listOf(Node(2), Node(3)))
            apply Node(4, listOf(Node(5), Node(6)))
    )   // 4 [5 [], 6 [], 8 [10 [], 12 []], 12 [15 [], 18 []]]

    println(Tree.pure({ x: Int, y: Int -> x * y }.curried())
            apply Node(4, listOf(Node(5), Node(6)))
            apply Node(1, listOf(Node(2), Node(3)))
    )   // 4 [8 [], 12 [], 5 [10 [], 15 []], 6 [12 [], 18 []]]

    println(Tree.pure({ x: Int, y: Int -> x * y }.curried())
            apply Node(1, listOf(Node(2, listOf(Node(3))), Node(4, listOf())))
            apply Node(5, listOf(Node(6), Node(7, listOf(Node(8), Node(9)))))
    )   // 5 [6 [], 7 [8 [], 9 []], 10 [12 [], 14 [16 [], 18 []], 15 [18 [], 21 [24 [], 27 []]]], 20 [24 [], 28 [32 [], 36 []]]]
}

sealed class Tree<out A> : Functor<A> {

    abstract override fun <B> fmap(f: (A) -> B): Functor<B>

    companion object
}

class Node<out A>(val value: A, val forest: List<Node<A>> = emptyList()) : Tree<A>() {

    override fun toString(): String = "$value $forest"

    override fun <B> fmap(f: (A) -> B): Node<B> = Node(f(value), forest.map { it.fmap(f) })
}

fun <A> Tree.Companion.pure(value: A) = Node(value)

infix fun <A, B> Node<(A) -> B>.apply(node: Node<A>): Node<B> = Node(
        value(node.value),
        node.forest.map { it.fmap(value) } + forest.map { it.apply(node) }
)

private fun <P1, P2, R> ((P1, P2) -> R).curried(): (P1) -> (P2) -> R =
        { p1: P1 -> { p2: P2 -> this(p1, p2) } }
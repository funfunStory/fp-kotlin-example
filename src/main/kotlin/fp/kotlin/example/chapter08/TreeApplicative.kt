package fp.kotlin.example.chapter08

import fp.kotlin.example.chapter07.Functor

fun main() {
    val tree = Node(1, listOf(Node(2), Node(3)))

    println(tree.fmap { it * 2 })   // 2 [4 [], 6 []]
    println(Tree.pure({ x: Int -> x * 2 }) apply tree)  // 2 [4 [], 6 []]

    println(Tree.pure({ x: Int, y: Int -> x * y }.curried())
        apply Node(1, listOf(Node(2), Node(3)))
        apply Node(4, listOf(Node(5), Node(6)))
    )   // 4 [5 [], 6 [], 8 [10 [], 12 []], 12 [15 [], 18 []]]
}

sealed class Tree<out A> : Functor<A> {

    abstract override fun <B> fmap(f: (A) -> B): Functor<B>

    companion object
}

data class Node<out A>(val value: A, val forest: List<Node<A>> = emptyList()) : Tree<A>() {

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
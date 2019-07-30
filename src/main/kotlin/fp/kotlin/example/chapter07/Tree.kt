package fp.kotlin.example.chapter07

fun main() {
    val tree = treeOf(1,
            treeOf(2,
                    treeOf(3), treeOf(4)),
            treeOf(5,
                    treeOf(6), treeOf(7)))

    println(tree)   // (N 1 (N 2 (N 3 E E) (N 4 E E)) (N 5 (N 6 E E) (N 7 E E)))

    val transformedTree = tree.fmap { it + 1 }

    println(transformedTree)    // (N 2 (N 3 (N 4 E E) (N 5 E E)) (N 6 (N 7 E E) (N 8 E E)))
}

fun <T> treeOf(value: T, leftTree: Tree<T> = EmptyTree, rightTree: Tree<T> = EmptyTree): Tree<T> =
        Node(value, leftTree, rightTree)

sealed class Tree<out A> : Functor<A> {

    abstract override fun toString(): String

    abstract override fun <B> fmap(f: (A) -> B): Tree<B>
}

data class Node<out A>(val value: A, val leftTree: Tree<A> = EmptyTree, val rightTree: Tree<A> = EmptyTree) : Tree<A>() {

    override fun toString(): String = "(N $value $leftTree $rightTree)"

    override fun <B> fmap(f: (A) -> B): Tree<B> =
            Node(f(value), leftTree.fmap(f), rightTree.fmap(f))
}

object EmptyTree : Tree<kotlin.Nothing>() {

    override fun toString(): String = "E"

    override fun <B> fmap(f: (kotlin.Nothing) -> B): Tree<B> = EmptyTree
}
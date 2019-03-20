package fp.kotlin.example.chapter10.solution

import fp.kotlin.example.chapter10.Monad

/**
 *
 * 연습문제 10-15
 *
 * 바이너리 트리 모나드 FunTree를 만들어 보자.
 *
 */

fun main() {
    val node1 = FunTree.pure(1)
    val node2 = FunTree.pure(2)
    val node3 = FunTree.pure(3)
    val node4 = FunTree.pure(4)
    val node5 = FunTree.pure(5)

    val fmapF: (Int) -> String = { "value is $it" }
    val flatMapF: (Int) -> FunTree<String> = {
        FunTree.Node("value is $it", FunTree.Node("value is ${it + 1}"), FunTree.Node("value is ${it + 2}"))
    }

    require(node1 == FunTree.Node(1))
    require(node2 == FunTree.Node(2))
    require(node3 == FunTree.Node(3))
    require(node4 == FunTree.Node(4))
    require(node5 == FunTree.Node(5))

    require(node1.fmap(fmapF) == FunTree.Node("value is 1"))
    require(node1.flatMap(flatMapF) == FunTree.Node("value is 1",
        FunTree.Node("value is 2"),
        FunTree.Node("value is 3"))
    )

    val tree1 = node1 mappend node2
    require(tree1 == FunTree.Node(1, FunTree.Node(2)))
    require(tree1.fmap(fmapF) == FunTree.Node("value is 1", FunTree.Node("value is 2")))
    require(tree1.flatMap(flatMapF) == FunTree.Node("value is 1",
        FunTree.Node("value is 2",
            FunTree.Node("value is 2",
                FunTree.Node("value is 3"),
                FunTree.Node("value is 4"))
        ),
        FunTree.Node("value is 3"))
    )
    require(tree1.leadTo(FunTree.pure(7)) == FunTree.pure(7))

    val tree2 = tree1 mappend node3
    require(tree2 == FunTree.Node(1,
        FunTree.Node(2), FunTree.Node(3))
    )
    require(tree2.fmap(fmapF) == FunTree.Node("value is 1",
        FunTree.Node("value is 2"), FunTree.Node("value is 3")))
    require(tree2.flatMap(flatMapF) == FunTree.Node("value is 1",
        FunTree.Node("value is 2",
            FunTree.Node("value is 2",
                FunTree.Node("value is 3"),
                FunTree.Node("value is 4")),
            FunTree.Node("value is 3",
                FunTree.Node("value is 4"),
                FunTree.Node("value is 5"))

        ),
        FunTree.Node("value is 3"))
    )
    require(tree2.leadTo(FunTree.pure(7)) == FunTree.pure(7))

    val tree3 = tree2 mappend node4
    require(tree3 == FunTree.Node(1,
        FunTree.Node(2,
            FunTree.Node(4)),
        FunTree.Node(3))
    )
    require(tree3.fmap(fmapF) == FunTree.Node("value is 1",
        FunTree.Node("value is 2",
            FunTree.Node("value is 4")),
        FunTree.Node("value is 3")))
    require(tree3.flatMap(flatMapF) == FunTree.Node("value is 1",
        FunTree.Node("value is 2",
            FunTree.Node("value is 2",
                FunTree.Node("value is 3",
                    FunTree.Node("value is 4",
                        FunTree.Node("value is 5"),
                        FunTree.Node("value is 6"))),
                FunTree.Node("value is 4")),
            FunTree.Node("value is 3",
                FunTree.Node("value is 4"),
                FunTree.Node("value is 5"))),
        FunTree.Node("value is 3")))
    require(tree3.leadTo(FunTree.pure(7)) == FunTree.pure(7))

    val tree4 = tree3 mappend node5
    require(tree4 == FunTree.Node(1,
        FunTree.Node(2,
            FunTree.Node(4), FunTree.Node(5)),
        FunTree.Node(3))
    )
    require(tree4.fmap(fmapF) == FunTree.Node("value is 1",
        FunTree.Node("value is 2",
            FunTree.Node("value is 4"), FunTree.Node("value is 5")),
        FunTree.Node("value is 3")))
    require(tree4.flatMap(flatMapF) == FunTree.Node("value is 1",
        FunTree.Node("value is 2",
            FunTree.Node("value is 2",
                FunTree.Node("value is 3",
                    FunTree.Node("value is 4",
                        FunTree.Node("value is 5"),
                        FunTree.Node("value is 6")),
                    FunTree.Node("value is 5",
                        FunTree.Node("value is 6"),
                        FunTree.Node("value is 7"))),
                FunTree.Node("value is 4")),
            FunTree.Node("value is 3",
                FunTree.Node("value is 4"),
                FunTree.Node("value is 5"))),
        FunTree.Node("value is 3")))
    require(tree4.leadTo(FunTree.pure(7)) == FunTree.pure(7))

    val tree5 = tree1 mappend tree3
    require(tree5 == FunTree.Node(1,
        FunTree.Node(2),
        FunTree.Node(1,
            FunTree.Node(2,
                FunTree.Node(4)),
            FunTree.Node(3)))
    )
    require(tree5.fmap(fmapF) == FunTree.Node("value is 1",
        FunTree.Node("value is 2"),
        FunTree.Node("value is 1",
            FunTree.Node("value is 2",
                FunTree.Node("value is 4")),
            FunTree.Node("value is 3")))
    )
    require(tree5.flatMap(flatMapF) == FunTree.Node("value is 1",
        FunTree.Node("value is 2",
            FunTree.Node("value is 2",
                FunTree.Node("value is 3"),
                FunTree.Node("value is 4")),
            FunTree.Node("value is 1",
                FunTree.Node("value is 2",
                    FunTree.Node("value is 2",
                        FunTree.Node("value is 3",
                            FunTree.Node("value is 4",
                                FunTree.Node("value is 5"),
                                FunTree.Node("value is 6"))),
                        FunTree.Node("value is 4")),
                    FunTree.Node("value is 3",
                        FunTree.Node("value is 4"),
                        FunTree.Node("value is 5"))),
                FunTree.Node("value is 3"))),
        FunTree.Node("value is 3"))
    )
    require(tree5.leadTo(FunTree.pure(7)) == FunTree.pure(7))
}

sealed class FunTree<out A> : Monad<A> {

    object EmptyTree : FunTree<Nothing>()

    data class Node<out A>(val value: A, val leftTree: FunTree<A> = EmptyTree, val rightTree: FunTree<A> = EmptyTree)
        : FunTree<A>()

    companion object {
        fun <A> pure(value: A): Node<A> = Node(0).pure(value) as Node<A>
    }

    override infix fun <V> pure(value: V): Monad<V> = Node(value)

    override infix fun <B> fmap(f: (A) -> B): Monad<B> = when (this) {
        EmptyTree -> EmptyTree
        is Node -> Node(f(value), leftTree.fmap(f) as FunTree<B>, rightTree.fmap(f) as FunTree<B>)
    }

    override infix fun <B> flatMap(f: (A) -> Monad<B>): Monad<B> = when (this) {
        EmptyTree -> EmptyTree
        is Node -> (f(value) as FunTree) mappend (leftTree.flatMap(f) as FunTree) mappend (rightTree.flatMap(
            f) as FunTree)
    }

    override fun <B> leadTo(m: Monad<B>): Monad<B> = m as FunTree<B>
}

fun <A> FunTree<A>.mempty(): FunTree<A> = FunTree.EmptyTree

infix fun <A> FunTree<A>.mappend(other: FunTree<A>): FunTree<A> = when (this) {
    FunTree.EmptyTree -> other
    is FunTree.Node -> when (other) {
        FunTree.EmptyTree -> this
        is FunTree.Node -> when (this.leftTree) {
            FunTree.EmptyTree -> FunTree.Node(value, other, rightTree)
            is FunTree.Node -> when (this.rightTree) {
                FunTree.EmptyTree -> FunTree.Node(value, leftTree, other)
                is FunTree.Node -> FunTree.Node(value, leftTree.mappend(other), rightTree)
            }
        }
    }
}
package fp.kotlin.example.chapter07

import fp.kotlin.example.chapter04.compose

fun main(args: Array<String>) {
    val f = { it: Int -> it + 1 }
    val g = { it: Int -> it * 2 }

    // Maybe 2 laws
    val nothingLeft = Nothing.fmap(f compose g)
    // throws NoSuchElementException
//    val nothingRight = Nothing.fmap(Nothing.fmap { g }.invoke() compose Nothing.fmap { f }.invoke())
    val nothingRight = Nothing.fmap(g).fmap(f)
    println(nothingLeft == nothingRight)  // true

    val justLeft = Just(5).fmap(f compose g)
//    val justRight = Just(5).fmap(Just(0).fmap { f }.invoke() compose Just(0).fmap { g }.invoke())
    val justRight = Just(5).fmap(g).fmap(f)
    println(justLeft == justRight)  // true

    // Tree 2 laws
    val tree = Node(1, Node(2, EmptyTree, EmptyTree), Node(3, EmptyTree, EmptyTree))

    println(EmptyTree.fmap(f compose g) == EmptyTree.fmap(g).fmap(f))  // true
    println(tree.fmap(f compose g) == tree.fmap(g).fmap(f))  // true

    // Either 2 laws
    println(Left("error").fmap(f compose g) == Left("error").fmap(g).fmap(f))  // true
    println(Right(5).fmap(f compose g) == Right(5).fmap(g).fmap(f))  // true
}


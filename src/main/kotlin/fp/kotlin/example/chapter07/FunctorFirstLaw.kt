package fp.kotlin.example.chapter07

fun main(args: Array<String>) {
    // Maybe 1 laws
    println(Nothing.fmap { identity(it) } == identity(Nothing))  // true
    println(Just(5).fmap { identity(it) } == identity(Just(5)))  // true

    // Tree 1 laws
    val tree = Node(1, Node(2, EmptyTree, EmptyTree), Node(3, EmptyTree, EmptyTree))

    println(EmptyTree.fmap { identity(it) } == identity(EmptyTree)) // true
    println(tree.fmap { identity(it) } == identity(tree)) // true

    // Either 1 laws
    println(Left("error").fmap { identity(it) } == identity(Left("error"))) // true
    println(Right(5).fmap { identity(it) } == identity(Right(5))) // true
}

fun <T> identity(x: T): T = x
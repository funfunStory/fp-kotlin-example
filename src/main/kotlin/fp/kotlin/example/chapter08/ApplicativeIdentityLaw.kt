package fp.kotlin.example.chapter08

// Identity
// pure(identity) apply af = af

fun main(args: Array<String>) {
    val maybeAf = Just(10)
    val leftMaybe = Maybe.pure(identity()) apply maybeAf
    println(leftMaybe.toString() == maybeAf.toString())   // true

    val treeAf = Node(1, listOf(Node(2), Node(3)))
    val leftTree = Tree.pure(identity()) apply treeAf
    println(leftTree.toString() == treeAf.toString())  // true

    val eitherAf = Right(10)
    val leftEither = Either.pure(identity()) apply eitherAf
    println(leftEither.toString() == eitherAf.toString())  // true
}

private fun identity() = { x: Int -> x }
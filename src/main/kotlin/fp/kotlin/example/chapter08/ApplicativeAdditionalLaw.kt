package fp.kotlin.example.chapter08

// pure(function) apply af = af.fmap(function)

fun main(args: Array<String>) {
    val function = { x: Int -> x * 2 }

    val maybeAf = Just(10)
    val leftMaybe = Maybe.pure(function) apply maybeAf
    val rightMaybe = maybeAf.fmap(function)
    println(leftMaybe.toString() == rightMaybe.toString())

    val treeAf = Node(1, listOf(Node(2), Node(3)))
    val leftTree = Tree.pure(function) apply treeAf
    val rightTree = treeAf.fmap(function)
    println(leftTree.toString() == rightTree.toString())

    val eitherAf = Right(10)
    val leftEither = Either.pure(function) apply eitherAf
    val rightEither = eitherAf.fmap(function)
    println(leftEither.toString() == rightEither.toString())
}
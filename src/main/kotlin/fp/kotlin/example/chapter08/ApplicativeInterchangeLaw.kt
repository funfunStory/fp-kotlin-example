package fp.kotlin.example.chapter08

// Interchange
// af apply pure(x) = pure(of(x)) apply af

fun main() {
    val x = 10

    val maybeAf = Just({ a: Int -> a * 2 })
    val leftMaybe = maybeAf apply Maybe.pure(x)
    val rightMaybe = Maybe.pure(of<Int, Int>(x)) apply maybeAf
    println(leftMaybe.toString() == rightMaybe.toString())  // true

    val treeAf = Node({ a: Int -> a * 2 })
    val leftTree = treeAf apply Tree.pure(x)
    val rightTree = Tree.pure(of<Int, Int>(x)) apply treeAf
    println(leftTree.toString() == rightTree.toString())    // true

    val eitherAf = Right({ a: Int -> a * 2 })
    val leftEither = eitherAf apply Either.pure(x)
    val rightEither = Either.pure(of<Int, Int>(x)) apply eitherAf
    println(leftEither.toString() == rightEither.toString())    // true
}

private fun <T, R> of(value: T) = { f: (T) -> R -> f(value) }
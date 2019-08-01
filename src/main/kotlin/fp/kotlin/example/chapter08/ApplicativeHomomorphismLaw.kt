package fp.kotlin.example.chapter08

// Homomorphism
// pure(function) apply pure(x) = pure(function(x))

fun main() {
    val function = { x: Int -> x * 2 }
    val x = 10

    val leftMaybe = Maybe.pure(function) apply Maybe.pure(10)
    val rightMaybe = Maybe.pure(function(x))
    println(leftMaybe.toString() == rightMaybe.toString())  // true

    val leftTree = Tree.pure(function) apply Tree.pure(10)
    val rightTree = Tree.pure(function(x))
    println(leftTree.toString() == rightTree.toString())    // true

    val leftEither = Either.pure(function) apply Either.pure(10)
    val rightEither = Either.pure(function(x))
    println(leftEither.toString() == rightEither.toString())    // true
}


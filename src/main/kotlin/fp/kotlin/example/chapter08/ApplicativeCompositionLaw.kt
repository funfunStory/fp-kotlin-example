package fp.kotlin.example.chapter08

// Composition
// pure(compose) apply af1 apply af2 apply af3 = af1 apply (af2 apply af3)

fun main(args: Array<String>) {
    val maybeAf1 = Just({ x: Int -> x * 2 })
    val maybeAf2 = Just({ x: Int -> x + 1})
    val maybeAf3 = Just(30)
    val leftMaybe = Maybe.pure(compose().curried()) apply maybeAf1 apply maybeAf2 apply maybeAf3
    val rightMaybe = maybeAf1 apply (maybeAf2 apply maybeAf3)
    println(leftMaybe.toString() == rightMaybe.toString())  // true

    val treeAf1 = Node({ x: Int -> x * 2 })
    val treeAf2 = Node({ x: Int -> x + 1})
    val treeAf3 = Node(10)
    val leftTree = Tree.pure(compose().curried()) apply treeAf1 apply treeAf2 apply treeAf3
    val rightTree = treeAf1 apply (treeAf2 apply treeAf3)
    println(leftTree.toString() == rightTree.toString())    // true

    val eitherAf1 = Right({ x: Int -> x * 2 })
    val eitherAf2 = Right({ x: Int -> x + 1})
    val eitherAf3 = Right(10)
    val leftEither = Either.pure(compose().curried()) apply eitherAf1 apply eitherAf2 apply eitherAf3
    val rightEither = eitherAf1 apply (eitherAf2 apply eitherAf3)
    println(leftEither.toString() == rightEither.toString())    // true
}

private fun compose() = { f: (Int) -> Int, g: (Int) -> Int, v: Int -> f(g(v)) }

private fun <P1, P2, P3, R> ((P1, P2, P3) -> R).curried(): (P1) -> (P2) -> (P3) -> R = {
    p1: P1 -> { p2: P2 -> { p3: P3 -> this(p1, p2, p3) } }
}
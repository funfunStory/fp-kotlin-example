package fp.kotlin.example.chapter04

fun main(args: Array<String>) {
    println(multiThree(1, 2, 3))    // 6

    val partial1 = multiThree(1)
    val partial2 = partial1(2)
    val partial3 = partial2(3)

    println(partial3) // 6

    println(multiThree(1)(2)(3))    // 6

    val multiThree = { a: Int, b: Int, c: Int -> a * b * c }
    val curried = multiThree.curried()
    println(curried(1)(2)(3))   // 6

    val uncurried = curried.uncurried()
    println(uncurried(1, 2, 3)) // 6
}

private fun multiThree(a: Int, b: Int, c: Int): Int = a * b * c

private fun multiThree(a: Int) = { b: Int -> { c: Int -> a * b * c } }

private fun <P1, P2, P3, R> ((P1, P2, P3) -> R).curried(): (P1) -> (P2) -> (P3) -> R =
        { p1: P1 -> { p2: P2 -> { p3: P3 -> this(p1, p2, p3) } } }

private fun <P1, P2, P3, R> ((P1) -> (P2) -> (P3) -> R).uncurried(): (P1, P2, P3) -> R =
        { p1: P1, p2: P2, p3: P3 -> this(p1)(p2)(p3) }
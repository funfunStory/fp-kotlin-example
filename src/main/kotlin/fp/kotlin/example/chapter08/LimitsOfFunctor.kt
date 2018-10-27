package fp.kotlin.example.chapter08

import fp.kotlin.example.chapter07.Just
import fp.kotlin.example.chapter07.Maybe

fun main(args: Array<String>) {
    val product: (Int, Int) -> Int = { x: Int , y: Int -> x * y}
//     Just(10).fmap(product)     // compile error

    val curriedProduct: (Int) -> (Int) -> Int = product.curried()
    val maybeProductTen: Maybe<(Int) -> Int> = Just(10).fmap(curriedProduct)

    println(maybeProductTen.fmap { it(5) })   // Just(50)
    println(maybeProductTen.fmap { f: (Int) -> Int -> f(5) })   // Just(50)

//    maybeProductTen.fmap { it(Just(5)) }  // compile error
}

private fun <P1, P2, R> ((P1, P2) -> R).curried(): (P1) -> (P2) -> R = { p1: P1 -> { p2: P2 -> this(p1, p2) } }
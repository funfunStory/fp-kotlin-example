package fp.kotlin.example.chapter10

import fp.kotlin.example.chapter04.compose


fun main(args: Array<String>) {
    val x = 10
    val f = { a: Int -> Just(a * 2) }
    val g = { a: Int -> Just(a + 1) }
    val h = { a: Int -> Just(a * 10) }
    val pure = { a: Int -> Just(a) }
    val m = Just(10)

    // Left Identity
    // pure(x) flatMap f = f(x)
    println(pure(x) flatMap f == f(x))  // true

    // Right Identity
    // m flatMap pure = m
    println(m flatMap pure == m)    // true

    // Associativity Law
    // (m flatMap f) flatMap g = m flatMap { x -> f(x) flatMap g }
    println((m flatMap f) flatMap g == m flatMap { a -> f(a) flatMap g } )  // true

    // identity compose f = f
    // f compose identity = f
    // (f compose g) compose h = f compose (g compose h)
    println((pure compose f)(10) == f(10))  // true
    println((f compose pure)(10) == f(10))  // true
    println(((f compose g) compose h)(10) == (f compose (g compose h))(10)) // true
}

private infix fun <F, G, R> ((F) -> Monad<R>).compose(g: (G) -> Monad<F>): (G) -> Monad<R> {
    return { gInput: G -> g(gInput) flatMap this }
}
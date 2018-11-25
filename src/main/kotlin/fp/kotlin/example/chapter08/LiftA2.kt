package fp.kotlin.example.chapter08

import fp.kotlin.example.chapter04.solution.curried
import fp.kotlin.example.chapter05.FunList
import fp.kotlin.example.chapter05.funListOf

fun main(args: Array<String>) {
    println(Just(10).fmap { listOf(it) })   // Just([10])

    println(Maybe.pure({ x: Int, y: FunList<Int> -> FunList.Cons(x, y) }.curried())
        apply Just(3)
        apply Just(funListOf(10))
    )   // Just(Cons(head=3, tail=Cons(head=10, tail=fp.kotlin.example.chapter05.FunList$Nil@85ede7b)))

    val lifted = liftA2 { x: Int, y: FunList<Int> -> FunList.Cons(x, y) }
    println(
            lifted(Just(3), Just(funListOf(10)))
    ) // Just(Cons(head=3, tail=Cons(head=10, tail=fp.kotlin.example.chapter05.FunList$Nil@85ede7b)))
}

fun <A, B, R> liftA2(binaryFunction: (A, B) -> R) = { f1: Maybe<A>, f2: Maybe<B> ->
    Maybe.pure(binaryFunction.curried()) apply f1 apply f2
}

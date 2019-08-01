package fp.kotlin.example.chapter08

import fp.kotlin.example.chapter04.solution.curried
import fp.kotlin.example.chapter05.*

fun main() {
    when (val result = sequenceA(funListOf(Just(10), Just(20)))) {
        is Nothing -> Nothing
        is Just -> printFunList(result.value)   // [10, 20]
    }

    when (val result = sequenceAByFoldRight(funListOf(Just(10), Just(20)))) {
        is Nothing -> Nothing
        is Just -> printFunList(result.value)   // [10, 20]
    }
}

fun <T> cons() = { x: T, xs: FunList<T> -> FunList.Cons(x, xs) }

fun <T> sequenceA(maybeList: FunList<Maybe<T>>): Maybe<FunList<T>> = when (maybeList) {
    is FunList.Nil -> Just(funListOf())
    is FunList.Cons -> Maybe.pure(cons<T>().curried()) apply maybeList.head apply sequenceA(maybeList.tail)
}

fun <T> sequenceAByFoldRight(maybeList: FunList<Maybe<T>>): Maybe<FunList<T>> =
        maybeList.foldRight(Maybe.pure(funListOf()), liftA2(cons()))
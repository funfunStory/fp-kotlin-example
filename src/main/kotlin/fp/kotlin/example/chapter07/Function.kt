package fp.kotlin.example.chapter07

import fp.kotlin.example.chapter04.compose

fun main(args: Array<String>) {
    val f = { x: Int -> x + 1 }
    val g = { x: Int -> x * 2 }

    val fg = UnaryFunction(g).fmap(f)
    println(fg.invoke(5))   // 11

    val k = { x: Int -> Just(x) }
    val kg = UnaryFunction(g).fmap(k)
    println(kg.invoke(5))   // Just(10)
}

data class UnaryFunction<in T, out R>(val g: (T) -> R) : Functor<R> {

//    override fun <R2> fmap(f: (R) -> R2): UnaryFunction<T, R2> {
//        return UnaryFunction { x: T -> f(g(x)) }
//    }
//    override fun <R2> fmap(f: (R) -> R2) = UnaryFunction { x: T -> f(g(x)) }
    override fun <R2> fmap(f: (R) -> R2) = UnaryFunction { x: T -> (f compose g)(x) }

    fun invoke(input: T): R = g(input)
}

package fp.kotlin.example.chapter09

import fp.kotlin.example.chapter05.FunList
import fp.kotlin.example.chapter05.foldRight

interface Monoid<T> {

    fun mempty(): T

    fun mappend(m1: T, m2: T): T
}

fun <T> Monoid<T>.mconcat(list: FunList<T>): T = list.foldRight(mempty(), ::mappend)


//interface Monoid<T> {
//
//    fun mempty(): Monoid<T>
//
//    fun mappend(m: Monoid<T>): Monoid<T>
//}
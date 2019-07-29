package fp.kotlin.example.chapter06

import fp.kotlin.example.chapter05.FunList
import fp.kotlin.example.chapter05.FunList.Cons
import fp.kotlin.example.chapter05.FunList.Nil
import fp.kotlin.example.chapter05.addHead
import fp.kotlin.example.chapter05.printFunList

fun main() {
    val reversed = reverse(Cons(1, Cons(2, Cons(3, Nil))), Nil)
    printFunList(reversed)
}

fun <T> reverse(list: FunList<T>, acc: FunList<T>): FunList<T> = when (list) {
    Nil -> acc
    is Cons -> reverse(list.tail, acc.addHead(list.head))
}
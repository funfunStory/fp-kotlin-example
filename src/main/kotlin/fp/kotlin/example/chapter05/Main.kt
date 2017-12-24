package fp.kotlin.example.chapter05

import fp.kotlin.example.chapter05.List.Cons
import fp.kotlin.example.chapter05.List.Nil

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        val list: List<Char> = Cons('a', Cons('b', Nil))

        //        val intList = TODO()
        val intList = Cons(1, Cons(2, Cons(3, Cons(4, Nil))))
    }
}
package fp.kotlin.example.chapter09

import fp.kotlin.example.chapter05.FunList
import fp.kotlin.example.chapter05.concat
import fp.kotlin.example.chapter05.funListOf
import fp.kotlin.example.chapter05.printFunList
import fp.kotlin.example.chapter09.solution.AnyMonoid
import fp.kotlin.example.chapter09.solution.FunListMonoid

fun main(args: Array<String>) {
    val tree = Node("a",
            Node("b",
                    Node("c"), Node("d")),
            Node("e",
                    Node("f"), Node("g")))

    println(tree.contains("c"))   // True
    println(tree.contains("z"))  // False

    printFunList(tree.toFunList())   // [c, b, d, a, f, e, g]
}

fun <A> BinaryTree<A>.contains(value: A) = foldMap({ it == value }, AnyMonoid())

fun <A> BinaryTree<A>.toFunList(): FunList<A> = foldMap({ funListOf(it) }, FunListMonoid())
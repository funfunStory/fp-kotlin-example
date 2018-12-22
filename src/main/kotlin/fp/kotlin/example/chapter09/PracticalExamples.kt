package fp.kotlin.example.chapter09

import fp.kotlin.example.chapter05.FunList
import fp.kotlin.example.chapter05.concat
import fp.kotlin.example.chapter05.funListOf
import fp.kotlin.example.chapter05.printFunList

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

// 명인님, 연습문제에서 추가하면 연습문제에 있는 AnyMonoid를 사용하도록 수정하고 이부분은 삭제해주세요.
class AnyMonoid : Monoid<Boolean> {

    override fun mempty(): Boolean = false

    override fun mappend(m1: Boolean, m2: Boolean): Boolean = m1 || m2
}

// 명인님, 연습문제에서 추가하면 연습문제에 있는 FunListMonoid를 사용하도록 수정하고 이부분은 삭제해주세요.
class FunListMonoid<T> : Monoid<FunList<T>> {

    override fun mempty(): FunList<T> = FunList.Nil

    override fun mappend(m1: FunList<T>, m2: FunList<T>): FunList<T> = m1 concat m2
}
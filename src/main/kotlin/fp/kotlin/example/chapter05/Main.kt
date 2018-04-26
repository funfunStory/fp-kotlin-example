package fp.kotlin.example.chapter05

import fp.kotlin.example.chapter05.FunList.Cons
import fp.kotlin.example.chapter05.FunList.Nil
import fp.kotlin.example.chapter05.solution.appendTail

fun main(args: Array<String>) {
    val intList = FunList.Cons(1, FunList.Cons(2, FunList.Cons(3, Nil)))
    val doubleList = Cons(1.0, Cons(2.0, Cons(3.0, Nil)))

    printFunList(add2(intList))         // [3, 4, 5]
    printFunList(product2(doubleList))  // [2.0, 4.0, 6.0]

    printFunList(intList.map { it + 2 })          // [3, 4, 5]
    printFunList(doubleList.map { it * 2 })       // [2.0, 4.0, 6.0]

    println(sum(intList))               // 6
    println(sumByFoldLeft(intList))     // 6

    println(intList.map { it + 3 }.filter { it % 2 == 0 }.sum())     // 10

    val lowerCharList = Cons('a', Cons('b', Cons('c', Nil)))
    printFunList(toUpper(lowerCharList))    // [A, B, C]

    printFunList(intList.mapByFoldLeft { it + 2 })  // [3, 4, 5]

    val intList2 = FunList.Cons(1, FunList.Cons(3, FunList.Cons(10, Nil)))
    println(intList2.foldRight(0) { x, y -> x - y })    // 8
    println(intList2.foldLeft(0) { x, y -> y - x })    // 8

    printFunList(intList.mapByFoldRight { it + 2 })  // [3, 4, 5]

    printFunList(intList.zipWith({ x, y -> x + y }, intList2))  // [2, 5, 13]
    printFunList(intList.zipWith({ x, y -> if (x > y) x else y }, intList2))  // [1, 3, 10]
    printFunList(intList.zipWith({ x, y -> x to y }, lowerCharList))  // [(1, a), (2, b), (3, c)]


}

tailrec fun add2(list: FunList<Int>, acc: FunList<Int> = FunList.Nil): FunList<Int> = when (list) {
    FunList.Nil -> acc
    is FunList.Cons -> add2(list.getTail(), acc.appendTail(list.getHead() + 2))
}

tailrec fun product2(list: FunList<Double>, acc: FunList<Double> = FunList.Nil): FunList<Double> = when (list) {
    FunList.Nil -> acc
    is FunList.Cons -> product2(list.getTail(), acc.appendTail(list.getHead() * 2))
}

fun sum(list: FunList<Int>): Int = when (list) {
    FunList.Nil -> 0
    is FunList.Cons -> list.getHead() + sum(list.getTail())
}

fun sumByFoldLeft(list: FunList<Int>): Int = list.foldLeft(0) { acc, x -> acc + x }

fun toUpper(list: FunList<Char>): FunList<Char> = list.foldLeft(Nil) { acc: FunList<Char>, char: Char ->
    acc.appendTail(char.toUpperCase())
}

tailrec fun <T> printFunList1(list: FunList<T>, acc: String = ""): Unit = when (list) {
    FunList.Nil -> println("[$acc]")
    is FunList.Cons -> if (acc.isEmpty()) {
        printFunList1(list.getTail(), "${list.getHead()}")
    } else {
        printFunList1(list.getTail(), "$acc, ${list.getHead()}")
    }
}

tailrec fun <T> printFunList2(list: FunList<T>, acc: String = ""): Unit = when (list) {
    FunList.Nil -> println("[${acc.drop(2)}]")
    is FunList.Cons -> printFunList2(list.getTail(), "$acc, ${list.getHead()}")
}

// 컴파일 에러가 발생
// fun <T> printFunList(list: FunList<T>): Unit = list.foldLeft("") { acc, x -> ... }

fun <T> printFunList(list: FunList<T>) {
    println("[${printByFoldLeft(list).drop(2)}]")
}

fun <T> printByFoldLeft(list: FunList<T>): String = list.foldLeft("") { acc, x -> "$acc, $x" }

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        val intList = Cons(1, Cons(2, Cons(3, Cons(4, Nil))))
        val lowerCharList = Cons('a', Cons('b', Cons('c', Cons('d', Nil))))

        println(intList.zip(lowerCharList))   // Cons((1,a), Cons((2,b), Cons((3,c), Cons((4,d), Nil))))

        val add3 = intList.map { it + 3 }
        val filterEven = add3.filter { it % 2 == 0 }
        val notChain = filterEven.sum()

        val chain = intList.map { it + 3 }
            .filter { it % 2 == 0 }
            .sum()

        require(chain == notChain)
    }
}
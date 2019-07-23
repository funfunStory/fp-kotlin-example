package fp.kotlin.example.chapter05

import fp.kotlin.example.chapter05.FunList.Nil
import fp.kotlin.example.chapter05.solution.filter

fun main() {
    val intList = funListOf(1, 2, 3)
    val doubleList = funListOf(1.0, 2.0, 3.0)

    printFunList(add3(intList))         // [4, 5, 6]
    printFunList(product3(doubleList))  // [3.0, 6.0, 9.0]

    printFunList(intList.map { it + 3 })          // [4, 5, 6]
    printFunList(doubleList.map { it * 3 })       // [3.0, 6.0, 9.0]

    println(sum(intList))               // 6
    println(sumByFoldLeft(intList))     // 6

    println(intList.map { it + 3 }.filter { it % 2 == 0 }.sum())     // 10

    val lowerCharList = funListOf('a', 'b', 'c')
    printFunList(toUpper(lowerCharList))    // [A, B, C]

    printFunList(intList.mapByFoldLeft { it + 3 })  // [4, 5, 6]

    val intList2 = funListOf(1, 3, 10)
    println(intList2.foldRight(0) { x, acc -> x - acc })    // 8
    println(intList2.foldLeft(0) { acc, x -> acc - x })    // -14

    printFunList(intList.mapByFoldRight { it + 3 })  // [4, 5, 6]

    printFunList(intList.zipWith({ x, y -> x + y }, intList2))  // [2, 5, 13]
    printFunList(intList.zipWith({ x, y -> if (x > y) x else y }, intList2))  // [1, 3, 10]
    printFunList(intList.zipWith({ x, y -> x to y }, lowerCharList))  // [(1, a), (2, b), (3, c)]

    println(funStreamOf(1, 2, 3).getHead())   // 1
    println(funStreamOf(1, 2, 3).getTail())   // Cons(head=() -> T, tail=() -> fp.kotlin.example.chapter05.FunStream<T>)

    println(funStreamOf(1, 2, 3, 4, 5).filter { it > 3 }.getHead())

    val infiniteVal = generateFunStream(0) { it + 5 }
    //    infiniteVal.forEach { print(it) }   // 0부터 계속 5씩 증가하는 값을 출력

    println(funListOf(1, 2, 3).concat(funListOf(4, 5, 6)))

}

fun add3(list: FunList<Int>): FunList<Int> = when (list) {
    FunList.Nil -> Nil
    is FunList.Cons -> FunList.Cons(list.head + 3, add3(list.tail))
}

fun product3(list: FunList<Double>): FunList<Double> = when (list) {
    FunList.Nil -> Nil
    is FunList.Cons -> FunList.Cons(list.head * 3, product3(list.tail))
}

fun sum(list: FunList<Int>): Int = when (list) {
    FunList.Nil -> 0
    is FunList.Cons -> list.head + sum(list.tail)
}

fun sumByFoldLeft(list: FunList<Int>): Int = list.foldLeft(0) { acc, x -> acc + x }

fun toUpper(list: FunList<Char>): FunList<Char> =
    list.foldLeft(Nil) { acc: FunList<Char>, char: Char -> acc.addHead(char.toUpperCase()) }
        .reverse()
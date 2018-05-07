package fp.kotlin.example.chapter05

fun main(args: Array<String>) {
    printFunList1(funListOf(1, 2, 3, 4))    // [1, 2, 3, 4]
    printFunList2(funListOf(1, 2, 3, 4))    // [1, 2, 3, 4]
    printFunList(funListOf(1, 2, 3, 4))    // [1, 2, 3, 4]
}

tailrec fun <T> printFunList1(list: FunList<T>, acc: String = ""): Unit = when (list) {
    FunList.Nil -> println("[$acc]")
    is FunList.Cons -> if (acc.isEmpty()) {
        printFunList1(list.tail, "${list.head}")
    } else {
        printFunList1(list.tail, "$acc, ${list.head}")
    }
}

tailrec fun <T> printFunList2(list: FunList<T>, acc: String = ""): Unit = when (list) {
    FunList.Nil -> println("[${acc.drop(2)}]")
    is FunList.Cons -> printFunList2(list.tail, "$acc, ${list.head}")
}

// 컴파일 에러가 발생
// fun <T> printFunList(list: FunList<T>): Unit = list.foldLeft("") { acc, x -> "$acc, $x" }

fun <T> printFunList(list: FunList<T>) {
    println("[${printByFoldLeft(list).drop(2)}]")
}

private fun <T> printByFoldLeft(list: FunList<T>): String = list.foldLeft("") { acc, x -> "$acc, $x" }

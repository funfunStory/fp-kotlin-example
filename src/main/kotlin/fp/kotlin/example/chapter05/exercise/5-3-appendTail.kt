import fp.kotlin.example.chapter05.FunList

fun main(args: Array<String>) {

    val intList = FunList.Cons(1, FunList.Cons(2, FunList.Cons(3, FunList.Nil)))
    require(intList.appendTail(4) == FunList.Cons(1, FunList.Cons(2, FunList.Cons(3, FunList.Cons(4, FunList.Nil)))))

}

fun <T> FunList<T>.appendTail(value: T): FunList<T> = kotlin.TODO()
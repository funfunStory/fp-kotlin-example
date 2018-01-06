package fp.kotlin.example.chapter03

fun func() {
    println("Hello")
    func()
}

//fun func(n: Int) {
//    when {
//        0 >= n -> return
//        else -> {
//            println("Hello")
//            func(n - 1)
//        }
//    }
//}

fun main(args: Array<String>) {
    println(func(5))
}

fun func(n: Int): Int {
    return when {
        0 >= n -> 0
        else -> n + func(n - 1)
    }
}
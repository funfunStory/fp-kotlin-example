package fp.kotlin.example.chapter01

val lazyValue: String by lazy {
    // 매우 오래걸리는 작업
    "hello"
}

fun main(args: Array<String>) {
    println(lazyValue)  // "hello" 출력
    println(lazyValue)  // "hello" 출력

    val infiniteValue = generateSequence(0) { it + 5 }
    infiniteValue.take(5).forEach { print("$it ") }   // "0 5 10 15 20 " 출력
}
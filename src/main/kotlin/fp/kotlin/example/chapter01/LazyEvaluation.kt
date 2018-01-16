package fp.kotlin.example.chapter01

fun main(args: Array<String>) {
    println(lazyValue)  // hello
}

val lazyValue: String by lazy {
    // 매우 오래걸리는 작업
    "hello"
}
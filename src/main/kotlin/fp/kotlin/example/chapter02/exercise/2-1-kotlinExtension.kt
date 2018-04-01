package fp.kotlin.example.chapter02.exercise

/**
 * 연습문제 2-1
 *
 * String 값을 입력받아 “Hello, 입력받은 값"이 출력하는 확장함수를 구현하라.
 * (World를 입력받으면 “Hello, World”, Kotlin을 입력받으면 “Hello, Kotlin” 을 출력)
 *
 */

fun main(args: Array<String>) {
    require("kotlin".addHelloPrefix() == "Hello, kotlin")
    require("FP".addHelloPrefix() == "Hello, FP")
}

private fun String.addHelloPrefix(): String = TODO()
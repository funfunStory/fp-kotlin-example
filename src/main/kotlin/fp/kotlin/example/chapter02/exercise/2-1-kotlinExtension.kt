package fp.kotlin.example.chapter02.exercise

/**
 * 연습문제 2-1
 *
 * ``String`` 값을 입력받아 "Hello, 입력받은 값"이 출력하는 확장함수를 구현하라. 예를 들어 "World"를 입력받으면 "Hello, World", "Kotlin"을
 * 입력받으면 "Hello, Kotlin"을 출력한다.
 */
fun main() {
    require("kotlin".addHelloPrefix() == "Hello, kotlin")
    require("FP".addHelloPrefix() == "Hello, FP")
}

private fun String.addHelloPrefix(): String = TODO()
package fp.kotlin.example.chapter10

import java.io.File

fun main(args: Array<String>) {
    val filePath = ClassLoader.getSystemResource("someArticle.txt").path
    println(getFirstWord(filePath))     // Why

    val lines = getLines(filePath)
    println(getFirstWord2(lines))    // Why
}

private fun getFirstWord(filePath: String): String = getFirstLine(filePath).split(" ").first()

private fun getFirstLine(filePath: String): String = File(filePath).readLines().first()

private fun getFirstWord2(lines: List<String>): String = lines.first().split(" ").first()

private fun getLines(filePath: String): List<String> = File(filePath).readLines()

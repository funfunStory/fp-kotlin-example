package fp.kotlin.example.chapter10

import java.io.File

fun main(args: Array<String>) {
    val filePath = ClassLoader.getSystemResource("someArticle.txt").path
    println(getFirstWord(filePath))     // Why

    val line = getFirstLine2(filePath)
    println(getFirstWord2(line))    // Why
}

private fun getFirstWord(filePath: String): String = getFirstLine(filePath).split(" ").first()

private fun getFirstLine(filePath: String): String = File(filePath).readLines().first()

private fun getFirstWord2(line: String): String = line.split(" ").first()

private fun getFirstLine2(filePath: String): String {
    val lines = File(filePath).readLines()
    return lines.first()
}

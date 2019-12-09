package fp.kotlin.example.chapter11.exception

fun main() {

    println(divideTenBy(0))    // -1
    println(subtractTenBy(10))  // 0

    val result = divSubTenBy(5)
    if (result == -1) {
        println("divSubTenBy(5) error")
    } else {
        println("divSubTenBy(5) returns $result")
    }   // divSubTenBy(5) returns 8

    val result2 = divSubTenBy(0)
    if (result2 == -1) {
        println("divSubTenBy(0) error")
    } else {
        println("divSubTenBy(0) returns $result2")
    }   // divSubTenBy(0) error
}

private fun divideTenBy(value: Int) = try {
    10 / value
} catch (e: Exception) {
    -1
}

private fun subtractTenBy(value: Int) = 10 - value

private fun divSubTenBy(value: Int): Int {
    val divByTen = divideTenBy(value)

    if (divByTen == -1) {
        return -1   // -1은 실패를 의미
    }

    return subtractTenBy(divByTen)  // -1은 정상적인 결과를 의미
}
package fp.kotlin.example.chapter04

fun main(args: Array<String>) {

    val result = callback("1")("2")("3")("4")("5")
    println(result)                 // 12345

    val result1 = callback("1")("2")
    println(result1("3")("4")("5")) // 12345
}

val callback: (String) -> (String) -> (String) -> (String) -> (String) -> String = { v1 ->
    { v2 ->
        { v3 ->
            { v4 ->
                { v5 ->
                    v1 + v2 + v3 + v4 + v5
                }
            }
        }
    }
}

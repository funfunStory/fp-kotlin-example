package fp.kotlin.example.chapter04

fun main(args: Array<String>) {
    val result = object : CallBack1 {
        override fun callBack(x1: String): CallBack2 {
            return object : CallBack2 {
                override fun callBack(x2: String): CallBack3 {
                    return object : CallBack3 {
                        override fun callBack(x3: String): CallBack4 {
                            return object : CallBack4 {
                                override fun callBack(x4: String): CallBack5 {
                                    return object : CallBack5 {
                                        override fun callBack(x5: String): String {
                                            return x1 + x2 + x2 + x3 + x4 + x5
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    println(result
        .callBack("1")
        .callBack("2")
        .callBack("3")
        .callBack("4")
        .callBack("5"))     // 12345
}

interface CallBack1 {
    fun callBack(x1: String): CallBack2
}

interface CallBack2 {
    fun callBack(x2: String): CallBack3
}

interface CallBack3 {
    fun callBack(x3: String): CallBack4
}

interface CallBack4 {
    fun callBack(x4: String): CallBack5
}

interface CallBack5 {
    fun callBack(x5: String): String
}
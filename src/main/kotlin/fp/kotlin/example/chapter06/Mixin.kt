package fp.kotlin.example.chapter06

interface Developer {
    val language: String

    fun writeCode() {
        println("write $language")
    }
}

interface Backend : Developer {
    fun operateEnvironment(): String {
        return "operateEnvironment"
    }

    override val language: String
        get() = "Haskell"
}

interface Frontend : Developer {
    fun drawUI(): String {
        return "drawUI"
    }

    override val language: String
        get() = "Elm"
}

class FullStack : Frontend, Backend {
    override val language: String
        get() = super<Frontend>.language + super<Backend>.language
}

fun main(args: Array<String>) {
    val frontend = object : Frontend {}
    val backend = object : Backend {}

    frontend.writeCode()    // write Elm
    backend.writeCode()     // write Haskell

    val fullStack = FullStack()

    fullStack.writeCode()  // write ElmHaskell
    println(fullStack.drawUI())     // drawUI
    println(fullStack.operateEnvironment()) // operateEnvironment
}
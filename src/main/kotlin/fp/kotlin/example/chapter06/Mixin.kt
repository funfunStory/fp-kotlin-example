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
        get() = "Java"
}

interface Frontend : Developer {

    fun drawUI(): String {
        return "drawUI"
    }

    override val language: String
        get() = "JavaScript"
}

class FullStack() : Frontend , Backend {

    override val language: String
        get() = super<Backend>.language + super<Frontend>.language

    override fun writeCode() {
        super<Backend>.writeCode()
        super<Frontend>.writeCode()
    }
}


class FullStack2(val frontend: Frontend, val backend: Backend) : Frontend by frontend, Backend by backend {

    override val language: String
        get() = frontend.language + backend.language

    override fun writeCode() {
        backend.writeCode()
        frontend.writeCode()
    }
}





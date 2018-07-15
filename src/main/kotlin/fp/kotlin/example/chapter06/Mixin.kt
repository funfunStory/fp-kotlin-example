package fp.kotlin.example.chapter06

interface Developer {
    val language: String

    fun writeCode()

    fun findBugs(): String {
        return "findBugs"
    }
}

abstract class Backend: Developer {

    override val language = "Java"

    fun operateEnvironment(): String {
        return "operateEnvironment"
    }
}

abstract class Frontend: Developer {
    override val language = "JavaScript"

    fun drawUI(): String {
        return "drawUI"
    }
}

// 명인님께서 예제 추가
//class FullStack(val frontend: Frontend, val backend: Backend): Frontend by frontend, Backend by backend {
//    override fun writeCode() {
//    }
//}





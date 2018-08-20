package fp.kotlin.example.chapter06

fun main(args: Array<String>) {

    toggle = true
    toggle = false

    caseLanguageInterface(Java())
    caseLanguageEnum(Language.KOTLIN)
}

private fun caseLanguageInterface(language: LanguageInterface) = when (language) {
    is Java -> {
        // doSomething
    }
    is Kotlin -> {
        // doSomething
    }
    is Scala -> {
        // doSomething
    }
    else -> {
        throw IllegalArgumentException("invalid type : $language")
    }
}

private fun caseLanguageEnum(language: Language) = when (language) {
    Language.JAVA -> {
        // doSomething
    }
    Language.KOTLIN -> {
        // doSomething
    }
    Language.SCALA -> {
        // doSomething
    }
    Language.HASKELL -> {
        // doSomething
    }
}


var toggle: Boolean = false

interface LanguageInterface

class Java : LanguageInterface
class Kotlin : LanguageInterface
class Scala : LanguageInterface
class Haskell : LanguageInterface

enum class Language {
    JAVA, KOTLIN, SCALA, HASKELL
}


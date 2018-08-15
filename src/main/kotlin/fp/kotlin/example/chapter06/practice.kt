package fp.kotlin.example.chapter06

fun main(args: Array<String>) {

    toggle = true
    toggle = false

    val languageInterface: LanguageInterface = Java()

    val result = when (languageInterface) {
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
            throw IllegalArgumentException("invalid type : $languageInterface")
        }
    }

    val language: Language = Language.KOTLIN

    val result2 = when (language) {
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


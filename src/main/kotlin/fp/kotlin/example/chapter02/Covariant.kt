package fp.kotlin.example.chapter02

interface Some<T> {
    fun something()
}

fun <T : JVM> upperBound(value: Some<T>) {}     // upper bound
fun covariant(value: Some<out JVM>) {}
fun contraVariant(value: Some<in JVM>) {}
fun invariant(value: Some<JVM>) {}

open class Language

open class JVM : Language()

class Kotlin : JVM()

fun main(args: Array<String>) {
    val language: Some<Language> = object : Some<Language> {
        override fun something() {
            println("language")
        }
    }

    val jvm: Some<JVM> = object : Some<JVM> {
        override fun something() {
            println("jvm")
        }
    }

    val kotlin: Some<Kotlin> = object : Some<Kotlin> {
        override fun something() {
            println("kotlin")
        }
    }

//    covariant(language)  // error
    covariant(jvm)
    covariant(kotlin)

    contraVariant(language)
    contraVariant(jvm)
//    contraVariant(kotlin)      // error

//    invariant(language)       // error
    invariant(jvm)
//    invariant(kotlin)         // error

}

class Persion<T : String>(val age: T)


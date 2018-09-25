package fp.kotlin.example.chapter02

interface Box<T>

open class Language
open class JVM : Language()
class Kotlin : JVM()

fun invariant(value: Box<JVM>) {}
fun covariant(value: Box<out JVM>) {}
fun <T : JVM> upperBound(value: Box<T>) {}
fun contravariant(value: Box<in JVM>) {}

fun main(args: Array<String>) {
    val languageBox = object : Box<Language> {}
    val jvmBox = object : Box<JVM> {}
    val kotlinBox = object : Box<Kotlin> {}

//    invariant(languageBox)    // compile error
    invariant(jvmBox)
//    invariant(kotlinBox)      // compile error

//    covariant(languageBox)    // compile error
    covariant(jvmBox)
    covariant(kotlinBox)

//    upperBound(languageBox)   // compile error
    upperBound(jvmBox)
    upperBound(kotlinBox)

    contravariant(languageBox)
    contravariant(jvmBox)
//    contravariant(kotlinBox)  // compile error
}

interface Box2<T> {
    fun read(): T
    fun write(value: T)
}

//interface Box2<out T> {
//    fun read(): T
//    fun write(value: T)    // compile error
//}

//interface Box2<in T> {
//    fun read(): T         // compile error
//    fun write(value: T)
//}
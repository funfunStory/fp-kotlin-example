package fp.kotlin.example.chapter06

interface Eq<in T> {
    fun equal(x: T, y: T): Boolean
    fun notEqual(x: T, y: T): Boolean
}
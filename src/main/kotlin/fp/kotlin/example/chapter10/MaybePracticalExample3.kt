package fp.kotlin.example.chapter10

class A3(val b: B3?)
class B3(val c: C3?)
class C3(val d: D3?)
class D3(val value: String?)

fun main() {
    val a = A3(B3(C3(D3("someValue"))))

    println(a.b?.c?.d?.value ?: "")     // someValue
}
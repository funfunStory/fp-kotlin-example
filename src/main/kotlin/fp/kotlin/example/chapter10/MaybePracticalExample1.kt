package fp.kotlin.example.chapter10

class A1(val b: B1?)
class B1(val c: C1?)
class C1(val d: D1?)
class D1(val value: String?)

fun main() {
    val a = A1(B1(C1(D1("someValue"))))

    println(getValueOfD1(a))     // someValue
}

private fun getValueOfD1(a: A1): String {
    val b = a.b
    if (b != null) {
        val c = b.c
        if (c != null) {
            val d = c.d
            if (d != null) {
                if (d.value != null) {
                    return d.value
                } else {
                    return ""
                }
            }
        }
    }

    return ""
}
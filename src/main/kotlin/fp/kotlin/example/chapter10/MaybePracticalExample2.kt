package fp.kotlin.example.chapter10

class A2(val b: Maybe<B2>)
class B2(val c: Maybe<C2>)
class C2(val d: Maybe<D2>)
class D2(val value: Maybe<String>)

fun main(args: Array<String>) {
    val a = A2(Just(B2(Just(C2(Just(D2(Just("someValue"))))))))
    val result = when (val maybe = getValueOfD2(a)) {
        is Just -> maybe.value
        Nothing -> ""
    }

    println(result)     // someValue
}

private fun getValueOfD2(a: A2): Maybe<String> = a.b
        .flatMap { it.c }
        .flatMap { it.d }
        .flatMap { it.value }  as Maybe<String>
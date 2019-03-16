package fp.kotlin.example.chapter11.testing

import fp.kotlin.example.chapter10.*
import fp.kotlin.example.head
import fp.kotlin.example.tail
import io.kotlintest.properties.Gen
import io.kotlintest.properties.forAll
import io.kotlintest.specs.StringSpec
import kotlin.random.Random

class FunListTest: StringSpec({

    "testReverse" {
        forAll { a: String ->
            reverse(reverse(a)) == a
        }
    }

    "testInvalidReverse" {
        forAll { a: String ->
            invalidReverse(reverse(a)) == a
        }
    }

    "reverseFunList" {
        forAll(500, FunIntListGen()) { list: FunList<Int> ->
            printFunList(list)
            list.reverse().reverse() == list
        }
    }
})

class FunIntListGen : Gen<FunList<Int>> {

    override fun constants(): Iterable<FunList<Int>> = listOf(Nil)

    override fun random(): Sequence<FunList<Int>> = generateSequence {
        val listSize = Random.nextInt(0, 10)
        val values = Gen.int().random().take(listSize)
        funListOf(*values.toList().toTypedArray())
    }
}

private fun reverse(str: String): String = when {
    str.isEmpty() -> ""
    else -> reverse(str.tail()) + str.head()
}

private fun invalidReverse(str: String): String = when {
    str.isEmpty() -> "a"
    else -> invalidReverse(str.tail()) + str.head()
}
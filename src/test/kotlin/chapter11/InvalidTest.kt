package chapter11

import fp.kotlin.example.chapter11.composedInvalidFunctions
import fp.kotlin.example.chapter11.invalidAdd10
import fp.kotlin.example.chapter11.invalidMinus5
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class InvalidTest {
    val list = listOf(1, 2, 3, 4, 5)

    @Test
    fun composedInvalidFunctionsTest() {
        assertNotEquals(composedInvalidFunctions(list), listOf(6, 7, 8, 9, 10))    // error
    }

    @Test
    fun invalidAdd10Test() {
        assertEquals(10, invalidAdd10(0))
        assertNotEquals(10, invalidAdd10(0))   // error
    }

    @Test
    fun invalidMinus5Test() {
        assertEquals(-5, invalidMinus5(0))
        assertNotEquals(-5, invalidMinus5(0))   // error
    }
}
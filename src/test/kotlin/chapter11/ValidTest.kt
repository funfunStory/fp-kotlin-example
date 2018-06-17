package chapter11

import fp.kotlin.example.chapter11.*
import kotlin.test.Test
import kotlin.test.assertEquals

class ValidTest {

    private val list = listOf(1, 2, 3, 4, 5)
    private val list2 = listOf(10, 2, 3, 4, 5)

    @Test
    fun testComposedFunctions() {
        assertEquals(listOf(144, 196, 256, 324, 400), composedFunctions(list))
        assertEquals(listOf(144, 196, 256, 324, 400), composedFunctions(list))
        assertEquals(listOf(900, 196, 256, 324, 400), composedFunctions(list2))
        assertEquals(listOf(900, 196, 256, 324, 400), composedFunctions(list2))
    }

    @Test
    fun testComposedFunctions2() {
        assertEquals(listOf(22, 24, 26, 28, 30), composedFunctions2(list))
        assertEquals(listOf(22, 24, 26, 28, 30), composedFunctions2(list))
        assertEquals(listOf(40, 24, 26, 28, 30), composedFunctions2(list2))
        assertEquals(listOf(40, 24, 26, 28, 30), composedFunctions2(list2))
    }

    @Test
    fun testComposedFunctions3() {
        assertEquals(listOf(64, 36, 16, 4, 0), composedFunctions3(list))
        assertEquals(listOf(64, 36, 16, 4, 0), composedFunctions3(list))
        assertEquals(listOf(100, 36, 16, 4, 0), composedFunctions3(list2))
        assertEquals(listOf(100, 36, 16, 4, 0), composedFunctions3(list2))
    }

    @Test
    fun testAdd10() {
        assertEquals(15, add10(5))
        assertEquals(15, add10(5))
        assertEquals(-15, add10(-25))
        assertEquals(-15, add10(-25))
        assertEquals(10, add10(0))
        assertEquals(10, add10(0))
    }

    @Test
    fun testMinus5() {
        assertEquals(0, minus5(5))
        assertEquals(0, minus5(5))
        assertEquals(-30, minus5(-25))
        assertEquals(-30, minus5(-25))
        assertEquals(-5, minus5(0))
        assertEquals(-5, minus5(0))
    }

    @Test
    fun testTwice() {
        assertEquals(10, twice(5))
        assertEquals(10, twice(5))
        assertEquals(-50, twice(-25))
        assertEquals(-50, twice(-25))
        assertEquals(0, twice(0))
        assertEquals(0, twice(0))
    }

    @Test
    fun testIsEven() {
        assertEquals(false, isEven(5))
        assertEquals(false, isEven(5))
        assertEquals(false, isEven(-25))
        assertEquals(false, isEven(-25))
        assertEquals(true, isEven(0))
        assertEquals(true, isEven(0))
    }

    @Test
    fun testSquar() {
        assertEquals(25, square(5))
        assertEquals(25, square(5))
        assertEquals(625, square(-25))
        assertEquals(625, square(-25))
        assertEquals(0, square(0))
        assertEquals(0, square(0))
    }
}
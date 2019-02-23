package fp.kotlin.example.chapter11.exception

import fp.kotlin.example.chapter10.Just
import fp.kotlin.example.chapter10.Maybe
import fp.kotlin.example.chapter10.Nothing

fun main(args: Array<String>) {
    when(val result = divSubTenBy(5)) {
        is Nothing -> println("divSubTenBy(5) error")
        is Just -> println("divSubTenBy(5) returns ${result.value}")
    }   // divSubTenBy(5) returns 8

    when(val result = divSubTenBy(0)) {
        is Nothing -> println("divSubTenBy(0) error")
        is Just -> println("divSubTenBy(0) returns ${result.value}")
    }   // divSubTenBy(0) error
}

private fun divideTenBy(value: Int): Maybe<Int> = try {
    Just(10 / value)
} catch (e: Exception) {
    Nothing
}

private fun subtractTenBy(value: Int) = 10 - value

private fun divSubTenBy(value: Int) = divideTenBy(value).fmap { subtractTenBy(it) }
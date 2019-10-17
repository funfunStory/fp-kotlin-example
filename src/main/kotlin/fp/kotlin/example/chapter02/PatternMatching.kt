package fp.kotlin.example.chapter02

fun main() {
    println(checkValue("kotlin"))   // kotlin
    println(checkValue(5))      // 1..10
    println(checkValue(15))     // 11 or 15
    println(checkValue(User("Joe", 76)))    // User
    println(checkValue("unknown"))  // SomeValue

    println(checkCondition("kotlin"))   // kotlin
    println(checkCondition(5))   // 1..10
    println(checkCondition(User("Joe", 76)))   // == User(Joe, 76)
    println(checkCondition(User("Sandy", 65)))   // is User
    println(checkCondition("unknown"))   // SomeValue

    println(sum(listOf(1, 3, 4)))   // 8
}

data class User(val name: String, val age: Int)

fun checkValue(value: Any) = when (value) {
    "kotlin" -> "kotlin"
    in 1..10 -> "1..10"
    11, 15 -> "11 or 15"
    is User -> "User"
    else -> "SomeValue"
}

fun checkCondition(value: Any) = when {
    value == "kotlin" -> "kotlin"
    value in 1..10 -> "1..10"
    value === User("Joe", 76) -> "=== User"
    value == User("Joe", 76) -> "== User(Joe, 76)"
    value is User -> "is User"
    else -> "SomeValue"
}

fun sum(numbers: List<Int>): Int = when {
    numbers.isEmpty() -> 0
    else -> numbers.first() + sum(numbers.drop(1))
}
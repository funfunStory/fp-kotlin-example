package fp.kotlin.example.chapter01

fun main(args: Array<String>) {

    // Maybe 예제
    val just = Maybe.Just(1)
    val (i) = just

    // destructure 예제
    data class Pet(val name: String, val age: Int)
    val alice = Pet("Alice", 6)
    val (name, age) = alice

    println("Hello World!")
}

// Maybe 만들기
sealed class Maybe<out T> {
    object None : Maybe<Nothing>()
    data class Just<T>(val t: T) : Maybe<T>()
}

// 패턴 매칭 예제
class User(val age: Int)

fun lookupFromDB(s: String): Maybe<User> = Maybe.None

fun printUser(username: String) {
    val rec = lookupFromDB(username)
    when (rec) {
        is Maybe.None -> println("not found")
        is Maybe.Just<User> -> println("${rec.t.age} years old")
    }
}


// imperative fashion
private fun cosFixpoint(): Double {
    var x = 1.0
    while (true) {
        val y = Math.cos(x)
        if (x == y) return y
        x = y
    }
}

// recursive manner
tailrec fun cosFixpoint(x: Double = 1.0): Double {
    val r = Math.cos(x)
    return if (x == r) x else cosFixpoint(r)
}


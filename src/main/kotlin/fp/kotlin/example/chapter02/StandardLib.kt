package fp.kotlin.example.chapter02

fun main() {
    let()   // Person(name=Kotlin, age=10)
    with()  // Person(name=Kotlin, age=10)
    run()   // Person(name=Kotlin, age=10)
    run2()  // Person(name=Kotlin, age=10)
    apply() // Person(name=Kotlin, age=10)
    also()  // Person(name=Kotlin, age=10)
    codeBlock()
}

data class Person(var name: String, var age: Int)

fun normal() {
    val person = Person("FP", 30)
    person.name = "Kotlin"
    person.age = 10
    println("$person")  // Person(name=Kotlin, age=10)
}

fun let() {
    val person = Person("FP", 30)
    val result = person.let {
        it.name = "Kotlin"
        it.age = 10
        it
    }
    println(result) // Person(name=Kotlin, age=10)
}

fun with() {
    val person = Person("FP", 30)
    val result = with(person) {
        name = "Kotlin"
        age = 10
        this
    }
    println(result) // Person(name=Kotlin, age=10)
}

fun run() {
    val person = Person("FP", 30)
    val result = person.run {
        name = "Kotlin"
        age = 10
        this
    }
    println(result) // Person(name=Kotlin, age=10)
}

fun run2() {
    val person = run {
        val name = "Kotlin"
        val age = 10
        Person(name, age)
    }
    println(person) // Person(name=Kotlin, age=10)
}

fun apply() {
    val person = Person("FP", 30)
    val result = person.apply {
        name = "Kotlin"
        age = 10
    }
    println(result) // Person(name=Kotlin, age=10)
}

fun also() {
    val person = Person("FP", 30)
    val result = person.also {
        it.name = "Kotlin"
        it.age = 10
    }
    println(result)         // Person(name=Kotlin, age=10)
}

fun codeBlock() {
    val person = Person("FP", 30)
    val name = "Java"

    person.let {
        println(it.name)    // FP
    }

    person.also {
        println(it.name)    // FP
    }

    person.run {
        println(name)       // Java
    }

    person.apply {
        println(name)       // Java
    }

    with(person) {
        println(name)       // Java
    }
}
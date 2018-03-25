package fp.kotlin.example.chapter02

fun main(args: Array<String>) {
    codeBlock()
}

data class Person(var name: String, var age: Int)

fun normal() {
    val person = Person("FP", 30)
    person.name = "Kotlin"
    person.age = 10
    println("person is : $person")  // person is : Person(name=Kotlin, age=10)
}

fun let() {
    val result = Person("FP", 30).let {
        it.name = "Kotlin"
        it.age = 10
        println("person is : $it")  // person is : Person(name=Kotlin, age=10)
    }
    println(result) // kotlin.Unit
}

fun run() {
    val result = Person("FP", 30).run {
        name = "Kotlin"
        age = 10
        println("person is : $this")  // person is : Person(name=Kotlin, age=10)
    }
    println(result) // kotlin.Unit
}

fun with() {
    val result = with(Person("FP", 30)) {
        name = "Kotlin"
        age = 10
        println("person is : $this")  // person is : Person(name=Kotlin, age=10)
    }
    println(result) // kotlin.Unit
}

fun apply() {
    val result = Person("FP", 30).apply {
        name = "Kotlin"
        age = 10
        println("person is : $this")    // person is : Person(name=Kotlin, age=10)
    }
    println(result) // Person(name=Kotlin, age=10)
}

fun also() {
    val result = Person("FP", 30).also {
        it.name = "Kotlin"
        it.age = 10
        println("person is : $it")      // person is : Person(name=Kotlin, age=10)
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
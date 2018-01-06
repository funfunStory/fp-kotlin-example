package fp.kotlin.example.chapter01

import java.util.*



class AddressBook {

    private val contacts: Set<Contact> = setOf()

    fun createContact(contact: Contact): Contact {

        return contact
    }

    fun changeGroup() {

    }
}

data class Contact(var name: String?, var number: String?)

fun main(args: Array<String>) {
    var line = "5 3 1 7 4"

    println(pureFunction("5 3 1 7 4"))
    println(impureFunction(""))

    var contact = Contact("Joe", "017-1234-5678")
    changeNumber(contact, "010-1234-5678")

    removeHyphen(contact)

    val result = transparent("Joe")
    printEx(result)
}

var someName: String = "Joe"

fun hello1() {
    println("Hello $someName")
}

fun hello2(name: String) {
    println("Hello $name")
}

fun transparent(name: String): String {
    return "Hello $name"
}

fun printEx(helloStr: String) {
    println(helloStr)
}




//data class Person(var name: String, var age: Int)
//
//// 인자로 들어온 객체의 상태를 변경
//fun addAge(person: Person, num: Int) {
//    person.age += num
//}

// 객체의 속성을 val로 선언하면 수정이 불가능함
data class Person(val name: String, val age: Int)

// Person 객체를 수정하지않고, 새로운 객체로 생성하여 반환
fun addAge(person: Person, num: Int): Person {
    return Person(person.name, person.age + num)
}

fun changeNumber(contact: Contact, number: String?) {
    contact.number = number
}

fun removeHyphen(contact: Contact) {
    contact.number?.replace("-", "")
}

fun pureFunction(line: String): String {
    return line.split(" ")
            .map { it.toInt() }
            .toList()
            .sorted()
            .joinToString(" ")
}

fun impureFunction(line: String): String {
    val nums = line.split(" ")

    val numList = arrayListOf<Int>()
    for (num in nums) {
        numList.add(num.toInt())
    }

    Collections.sort(numList)
    return numList.joinToString(" ")
}


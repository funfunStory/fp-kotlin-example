package fp.kotlin.example.chapter11.testing

import fp.kotlin.example.chapter10.Just
import fp.kotlin.example.chapter10.Maybe
import fp.kotlin.example.chapter10.Nothing
import java.lang.IllegalStateException

fun main() {
    val immutablePerson = ImmutablePerson("Tom", 5)
    require(checkName(immutablePerson, "Tom"))
    require(!checkName(immutablePerson, "John"))

    val mutablePerson = MutablePerson("Tom", 5)
    require(checkName(mutablePerson, "Tom"))
    require(!checkName(immutablePerson, "John"))

    val person = Person(1, "Tom", 5)
    val db = MockDatabase(mapOf(1 to person))
//    val validName = getPersonNameFromDB(1, db)
//    val result = checkName(person, validName)

    val validName = getPersonNameFromDB(1, db)
    val result = checkName(person, validName)

    val maybePerson = getPersonFromDB(1, db)
    val result2 = checkName(maybePerson, validName)

    when(maybePerson) {
        is Just -> require(maybePerson.value == person)
        is Nothing -> error("error")
    }
}

data class ImmutablePerson(val name: String, val age: Int)

fun checkName(person: ImmutablePerson, validName: String): Boolean {
    return person.name == validName
}

data class MutablePerson(var name: String, var age: Int)

fun checkName(person: MutablePerson, validName: String): Boolean {
    return person.name == validName
}

fun rename(person: MutablePerson, name: String) {
    person.name = name
}

data class Person(val id: Int, val name: String, val age: Int)

interface Database {
    fun getPerson(id: Int): Person?
}

data class MockDatabase(private val persons: Map<Int, Person>): Database {

    override fun getPerson(id: Int): Person? {
        return persons[id]
    }
}

fun checkName(person: Person, db: Database): Boolean {
    return person.name == db.getPerson(person.id)?.name
}

//fun getPersonNameFromDB(id: Int, db: Database): String? {
//    return db.getPerson(id)?.name
//}

//fun checkName(person: Person, validName: String?): Boolean {
//    return validName.equals(person.name)
//}

fun getPersonNameFromDB(id: Int, db: Database): String {
    return db.getPerson(id)?.name ?: throw IllegalStateException()
}

fun checkName(person: Person, validName: String): Boolean {
    return validName.equals(person.name)
}

fun getPersonFromDB(id: Int, db: Database): Maybe<Person> = try {
    val person = db.getPerson(id)
    if (person == null) Nothing else Just(person)
} catch (e: Exception) {
    Nothing
}

fun checkName(maybePerson: Maybe<Person>, validName: String) = when (maybePerson) {
    is Nothing -> false
    is Just -> validName == maybePerson.value.name
}
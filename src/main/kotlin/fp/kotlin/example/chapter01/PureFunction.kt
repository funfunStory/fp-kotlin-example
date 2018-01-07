package fp.kotlin.example.chapter01

var z = 10

// 순수하지 않은 함수
fun impureFunction(x: Int, y: Int): Int = x + y + z

// 순수한 함수
fun pureFunction(x: Int, y: Int): Int = x + y

// 부수 효과가 있는 함수
fun withSideEffect(x: Int, y: Int): Int {
    z = y
    return x + y
}

data class MutablePerson(var name: String, var age: Int)

// 인자로 들어온 객체의 상태를 변경
fun addAge(person: MutablePerson, num: Int) {
    person.age += num
}

// 객체의 속성을 val로 선언하면 수정이 불가능함
data class ImmutablePerson(val name: String, val age: Int)

// Person 객체를 수정하지않고, 새로운 객체로 생성하여 반환
fun addAge(person: ImmutablePerson, num: Int): ImmutablePerson {
    return ImmutablePerson(person.name, person.age + num)
}
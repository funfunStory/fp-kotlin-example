package fp.kotlin.example.chapter01

// Any를 함수의 매개변수로 넘길수 있다
fun doSomethingWithAny(any: Any) {
    // do something
}

// Any를 함수의 리턴값으로 돌려줄 수 있다
fun doSomethingWithAny(): Any {
    return Any()
}

// Any를 List 자료구조에 담을 수 있다
var anyList: List<Any> = listOf(Any())


// 함수를 함수의 매개변수로 넘길수 있다
fun doSomething(func: (Int) -> String) {
    // do something
}

// 함수를 함수의 리턴값으로 돌려줄 수 있다
fun doSomething(): (Int) -> String {
    return { value -> value.toString() }
}

// 함수를 List 자료구조에 담을 수 있다
var funcList: List<(Int) -> String> = listOf({ value -> value.toString() })
package fp.kotlin.example.chapter09.exercise

/**
 *
 * 연습문제 9-13
 *
 * ``foldMap`` 함수를 사용하여 Foldable 트리에 ``contains`` 함수를 구현해보자.
 *
 */

fun main() {

    val tree = Node(1,
        Cons(Node(2, Cons(Node(3), Cons(Node(4), Nil))),
            Cons(Node(5, Cons(Node(6), Cons(Node(7), Nil))), Nil)))

    val tree2 = Node('a',
        Cons(Node('b', Cons(Node('c'), Cons(Node('d'), Nil))),
            Cons(Node('e', Cons(Node('f'), Cons(Node('g'), Nil))), Nil)))

    require(tree.contains(1))
    require(!tree.contains(8))

    require(tree2.contains('c'))
    require(!tree2.contains('x'))
}

fun <T> Tree<T>.contains(value: T): Boolean = TODO()

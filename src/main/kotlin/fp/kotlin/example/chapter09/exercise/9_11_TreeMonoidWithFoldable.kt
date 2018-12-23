package fp.kotlin.example.chapter09.exercise

import fp.kotlin.example.chapter09.Foldable
import fp.kotlin.example.chapter09.SumMonoid

/**
 *
 * 연습문제 9-11
 *
 * 일반 트리를 Foldable 타입클래스의 인스턴스로 만들고, ``foldLeft``, ``foldMap`` 함수의 동작을 테스트해보자.
 * 이때 트리의 노드는 값 ``val value: A``와 하위 트리의 리스트인 ``val forest: FunList<Node<A>>``를 프로퍼티로 가진다.
 *
 * 힌트 : 후위 탐색(post-order) 로 트리를 순회한다.
 *
 */

fun main(args: Array<String>) {

    val node = Node(1)
    val node2 = Node(1, Cons(Node(2), Nil))
    val node3 = Node(1, Cons(Node(2), Cons(Node(3), Nil)))
    val node4 = Node(1,
        Cons(Node(2), Cons(Node(3), Cons(Node(4), Nil))))
    val node5 = Node(1,
        Cons(Node(2), Cons(Node(3), Cons(Node(4), Cons(Node(5), Nil)))))
    val node6 =
        Node(1,
            Cons(Node(2, Cons(Node(6), Nil)),
                Cons(Node(3),
                    Cons(Node(4),
                        Cons(Node(5), Nil)))))

    val node7 =
        Node(1,
            Cons(Node(2, Cons(Node(6), Nil)),
                Cons(Node(3, Cons(Node(7), Nil)),
                    Cons(Node(4, Cons(Node(8), Nil)),
                        Cons(Node(5, Cons(Node(9), Cons(Node(10), Nil))), Nil)))))

    require(node.foldLeft(0) { acc, value -> acc + value } == 1)
    require(node2.foldLeft(0) { acc, value -> acc + value } == 3)
    require(node3.foldLeft(0) { acc, value -> acc + value } == 6)
    require(node4.foldLeft(0) { acc, value -> acc + value } == 10)
    require(node5.foldLeft(0) { acc, value -> acc + value } == 15)
    require(node6.foldLeft(0) { acc, value -> acc + value } == 21)
    require(node7.foldLeft(0) { acc, value -> acc + value } == 55)

    require(node.foldMap({ it * 2 }, SumMonoid()) == 2)
    require(node2.foldMap({ it * 2 }, SumMonoid()) == 6)
    require(node3.foldMap({ it * 2 }, SumMonoid()) == 12)
    require(node4.foldMap({ it * 2 }, SumMonoid()) == 20)
    require(node5.foldMap({ it * 2 }, SumMonoid()) == 30)
    require(node6.foldMap({ it * 2 }, SumMonoid()) == 42)
    require(node7.foldMap({ it * 2 }, SumMonoid()) == 110)

}

sealed class Tree<out T> : Foldable<T>

data class Node<out T>(val value: T, val forest: FunList<Node<T>> = Nil) : Tree<T>() {

    override fun <B> foldLeft(acc: B, f: (B, T) -> B): B = TODO()

}
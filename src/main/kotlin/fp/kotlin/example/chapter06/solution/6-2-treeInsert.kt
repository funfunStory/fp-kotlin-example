package fp.kotlin.example.chapter06.solution

/**
 * 6-1에서 만든 이진 트리에 노드를 추가하는 insert 함수를 Tree의 확장 함수를 만들어보자.
 * 이때 노드의 왼쪽 노드의 값은 오른쪽 노드의 값보다 항상 작아야 한다.
 *
 * 주의사항 : 값 비교를 위해서는 T가 항상 Comparable 속성을 가지고 있어야 한다.
 *          하지만 Tree를 복잡하게 구현하지 않기위해 생략하고, insert 함수는 Int 타입으로 타입을 제한한다.
 *
 * 힌트: 함수의 선언 타입은 아래와 같다.
 *
 */

fun main(args: Array<String>) {
    val tree1 = EmptyTree.insert(5)
    require(tree1 == Node(5))

    val tree2 = tree1.insert(3)
    require(tree2 == Node(5, Node(3)))

    val tree3 = tree2.insert(10)
    require(tree3 == Node(5, Node(3), Node(10)))
}

fun Tree<Int>.insert(elem: Int): Tree<Int> = when (this) {
    EmptyTree -> Node(elem, EmptyTree, EmptyTree)
    is Node -> if (elem <= value) {
        Node(value, left.insert(elem), right)
    } else {
        Node(value, left, right.insert(elem))
    }
}

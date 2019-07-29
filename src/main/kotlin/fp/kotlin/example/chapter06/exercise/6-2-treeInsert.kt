package fp.kotlin.example.chapter06.exercise

/**
 *
 * 연습문제 6-2
 *
 * 6-1에서 만든 이진 트리에 노드를 추가하는 insert 함수를 Tree의 확장 함수로 만들어보자.
 * 이때 노드의 왼쪽 하위  노드의 값은 오른쪽 하위 노드의 값보다 항상 작아야 한다.
 *
 * 단, 값을 비교하기 위해서는 ``T``가 항상 ``Comparable`` 속성을 가지고 있어야 한다.
 * 여기에서는 문제의 복잡도를 낮추기 위해 입력 타입을 ``Int``로 제한한다.
 *
 * 힌트: 함수의 선언 타입은 아래와 같다.
 */

fun main() {
    /*
     * 주석을 해제하고 insert()를 구현해보세요.


    val tree1 = EmptyTree.insert(5)
    require(tree1 == Node(5, EmptyTree, EmptyTree))

    val tree2 = tree1.insert(3)
    require(tree2 ==
        Node(5,
            Node(3, EmptyTree, EmptyTree),
            EmptyTree)
    )

    val tree3 = tree2.insert(10)
    require(tree3 ==
        Node(5,
            Node(3, EmptyTree, EmptyTree),
            Node(10, EmptyTree, EmptyTree)
        )
    )

    val tree4 = tree3.insert(20)
    require(tree4 ==
        Node(5,
            Node(3, EmptyTree, EmptyTree),
            Node(10,
                EmptyTree,
                Node(20, EmptyTree, EmptyTree)
            )
        )
    )

    val tree5 = tree4.insert(4)
    require(tree5 ==
        Node(5,
            Node(3,
                EmptyTree,
                Node(4, EmptyTree, EmptyTree)),
            Node(10,
                EmptyTree,
                Node(20, EmptyTree, EmptyTree)
            )
        )
    )

    val tree6 = tree5.insert(2)
    require(tree6 ==
        Node(5,
            Node(3,
                Node(2, EmptyTree, EmptyTree),
                Node(4, EmptyTree, EmptyTree)
            ),
            Node(10,
                EmptyTree,
                Node(20, EmptyTree, EmptyTree)
            )
        )
    )

    val tree7 = tree6.insert(8)
    require(tree7 ==
        Node(5,
            Node(3,
                Node(2, EmptyTree, EmptyTree),
                Node(4, EmptyTree, EmptyTree)
            ),
            Node(10,
                Node(8, EmptyTree, EmptyTree),
                Node(20, EmptyTree, EmptyTree)
            )
        )
    )
     */
}

fun Tree<Int>.insert(elem: Int): Tree<Int> = TODO()
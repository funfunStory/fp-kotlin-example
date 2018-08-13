package fp.kotlin.example.chapter06.exercise

/**
 * 6-1에서 만든 이진 트리에 어떤 노드가 존재하는지 확인하는 contains 함수를 추가해보자.
 *
 * 주의사항 : 문제의 복잡도를 낮추기 위해 연습문제 6-3도 타입을 Int로 제한한다.
 *
 * 힌트: 함수의 선언 타입은 아래와 같다.
 *
 */

fun main(args: Array<String>) {

    /*
     * 주석을 해제하고 insert()를 구현해보세요.
     */

//    require(!EmptyTree.contains(5))
//
//    val tree1 = EmptyTree.insert(5)
//    require(tree1.contains(5))
//    require(!tree1.contains(10))
//
//    val tree2 = tree1.insert(3)
//    require(tree2.contains(5))
//    require(tree2.contains(3))
//    require(!tree2.contains(10))
//
//    val tree3 = tree2.insert(10)
//    require(tree3.contains(5))
//    require(tree3.contains(3))
//    require(tree3.contains(10))
}

fun Tree<Int>.contains(elem: Int): Boolean = TODO()
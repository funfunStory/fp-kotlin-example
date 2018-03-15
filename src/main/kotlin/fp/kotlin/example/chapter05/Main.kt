package fp.kotlin.example.chapter05

import fp.kotlin.example.chapter05.solution.List
import fp.kotlin.example.chapter05.solution.List.Cons
import fp.kotlin.example.chapter05.solution.List.Nil
import fp.kotlin.example.chapter05.solution.add2
import fp.kotlin.example.chapter05.solution.addHead
import fp.kotlin.example.chapter05.solution.appendTail
import fp.kotlin.example.chapter05.solution.drop
import fp.kotlin.example.chapter05.solution.filter
import fp.kotlin.example.chapter05.solution.foldLeft
import fp.kotlin.example.chapter05.solution.getHead
import fp.kotlin.example.chapter05.solution.getTail
import fp.kotlin.example.chapter05.solution.map
import fp.kotlin.example.chapter05.solution.product
import fp.kotlin.example.chapter05.solution.product2
import fp.kotlin.example.chapter05.solution.sum
import fp.kotlin.example.chapter05.solution.take
import fp.kotlin.example.chapter05.solution.zip

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        val list: List<Char> = Cons('a', Cons('b', Nil))

        val intList = Cons(1, Cons(2, Cons(3, Cons(4, Nil))))
        val doubleList = Cons(1.0, Cons(2.0, Cons(3.0, Cons(4.0, Nil))))

        require(List.sum(intList) == 10)
        require(List.product(doubleList) == 24.0)

        require(List.appendTail(intList, 5) == Cons(1, Cons(2, Cons(3, Cons(4, Cons(5, Nil))))))
        require(
            List.addHead(intList, 0) == Cons(0, Cons(1, Cons(2, Cons(3, Cons(4, Nil))))))

        require(List.filter(intList, { x: Int -> x > 3 }) == Cons(4, Nil))
        require(
            List.filter(intList, { x: Int -> x % 2 == 0 }) == Cons(2, Cons(4, Nil)))

        require(List.drop(intList, 1) == Cons(2, Cons(3, Cons(4, Nil))))
        require(List.drop(Cons(1, Nil), 2) == Nil)

        require(List.take(intList, 2) == Cons(1, Cons(2, Nil)))
        require(List.take(Cons(1, Nil), 2) == Cons(1, Nil))

        require(List.add2(intList) == Cons(3, Cons(4, Cons(5, Cons(6, Nil)))))
        require((List.product2(doubleList) == Cons(2.0, Cons(4.0, Cons(6.0, Cons(8.0, Nil))))))
        require(List.add2(intList) == List.map(intList, { it + 2 }))    // ok
        require(
            List.product2(doubleList) == List.map(doubleList, { it * 2 }))
        println(List.map(intList, { it * 3 }))

//        println(List.sum(intList))  // 10
        require(List.sum(intList) == 10)  // 10

        println(List.foldLeft(intList, { acc, x -> acc + x }, 0)) // 10
        require(
            List.foldLeft(intList, { acc, x -> acc + x }, 0) == List.sum(intList))

        println(List.product(doubleList))  // 24.0
        println(List.foldLeft(doubleList, { acc, x -> acc * x }, 1.0)) // 24.0
        require(
            List.foldLeft(doubleList, { acc, x -> acc * x }, 1.0) == List.product(doubleList))

        val lowerCharList = Cons('a', Cons('b', Cons('c', Cons('d', Nil))))
        val upperCharList = Cons('A', Cons('B', Cons('C', Cons('D', Nil))))
        require(
            List.foldLeft(lowerCharList, { acc: List<Char>, char -> List.appendTail(acc, char.toUpperCase()) },
            Nil) == upperCharList)

        val sum = { acc: Int, value: Int -> acc + value }
        val product = { acc: Double, value: Double -> acc * value }
        val upper = { acc: List<Char>, char: Char -> List.appendTail(acc, char.toUpperCase()) }

        println(List.foldLeft(intList, sum, 0)) // 10
        println(List.foldLeft(doubleList, product, 1.0)) // 24.0
        println(List.foldLeft(lowerCharList, upper, Nil)) // Cons('A', Cons('B', Cons('C', Cons('D', Nil))))

        println(List.zip(intList, lowerCharList))   // Cons((1,a), Cons((2,b), Cons((3,c), Cons((4,d), Nil))))

        require(intList.sum() == List.sum(intList))
        require(doubleList.product() == List.product(doubleList))

        require(intList.getHead() == List.getHead(intList))
        require(intList.getTail() == List.getTail(intList))

        require(intList.appendTail(5) == List.appendTail(intList, 5))
        require(intList.addHead(0) == List.addHead(intList, 0))

        require(intList.filter({ x: Int -> x > 3 }) == Cons(4, Nil))
        require(intList.filter({ x: Int -> x % 2 == 0 }) == Cons(2, Cons(4, Nil)))

        require(intList.drop(1) == Cons(2, Cons(3, Cons(4, Nil))))
        require(Cons(1, Nil).drop(2) == Nil)

        require(intList.take(2) == Cons(1, Cons(2, Nil)))
        require(Cons(1, Nil).take(2) == Cons(1, Nil))

        require(intList.add2() == Cons(3, Cons(4, Cons(5, Cons(6, Nil)))))
        require((doubleList.product2() == Cons(2.0, Cons(4.0, Cons(6.0, Cons(8.0, Nil))))))
        require(intList.add2() == intList.map({ it + 2 }))    // ok
        require(doubleList.product2() == doubleList.map({ it * 2 }))
        println(intList.map({ it * 3 }))

        println(intList.sum())  // 10
        println(intList.foldLeft({ acc, x -> acc + x }, 0)) // 10
        require(intList.foldLeft({ acc, x -> acc + x }, 0) == intList.sum())

        println(doubleList.product())  // 24.0
        println(doubleList.foldLeft({ acc, x -> acc * x }, 1.0)) // 24.0
        require(doubleList.foldLeft({ acc, x -> acc * x }, 1.0) == doubleList.product())

        require(lowerCharList.foldLeft({ acc: List<Char>, char -> acc.appendTail(char.toUpperCase()) },
            Nil) == upperCharList)

        println(intList.foldLeft(sum, 0)) // 10
        println(doubleList.foldLeft(product, 1.0)) // 24.0
        println(lowerCharList.foldLeft(upper, Nil)) // Cons('A', Cons('B', Cons('C', Cons('D', Nil))))

        println(intList.zip(lowerCharList))   // Cons((1,a), Cons((2,b), Cons((3,c), Cons((4,d), Nil))))

        val add3 = intList.map({ it + 3 })
        val filterEven = add3.filter({ it % 2 == 0 })
        val notChain = filterEven.sum()

        val chain = intList.map({ it + 3 }).filter({ it % 2 == 0 }).sum()

        require(chain == notChain)
    }
}
package fp.kotlin.example.chapter05

import fp.kotlin.example.chapter05.FunList.Cons
import fp.kotlin.example.chapter05.FunList.Nil
import fp.kotlin.example.chapter05.solution.appendTail

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        val intList = Cons(1, Cons(2, Cons(3, Cons(4, Nil))))
        val doubleList = Cons(1.0, Cons(2.0, Cons(3.0, Cons(4.0, Nil))))

        require(intList.sum() == 10)
        require(doubleList.product() == 24.0)

        require(intList.getHead() == 1)
        require(intList.getTail() == Cons(2, Cons(3, Cons(4, Nil))))

        require(intList.appendTail(5) == Cons(1, Cons(2, Cons(3, Cons(4, Cons(5, Nil))))))
        //        require(intList.addHead(0) == Cons(0, Cons(1, Cons(2, Cons(3, Cons(4, Nil))))))

        require(intList.filter { x: Int -> x > 3 } == Cons(4, Nil))
        require(intList.filter { x: Int -> x % 2 == 0 } == Cons(2, Cons(4, Nil)))

        require(intList.drop(1) == Cons(2, Cons(3, Cons(4, Nil))))
        require(Cons(1, Nil).drop(2) == Nil)

        require(intList.take(2) == Cons(1, Cons(2, Nil)))
        require(Cons(1, Nil).take(2) == Cons(1, Nil))

        require(intList.add2() == Cons(3, Cons(4, Cons(5, Cons(6, Nil)))))
        require((doubleList.product2() == Cons(2.0, Cons(4.0, Cons(6.0, Cons(8.0, Nil))))))
        require(intList.add2() == intList.map { it + 2 })    // ok
        require(doubleList.product2() == doubleList.map { it * 2 })
        println(intList.map { it * 3 })

        println(intList.sum())  // 10
        println(intList.foldLeft(0) { acc, x -> acc + x }) // 10
        require(intList.foldLeft(0) { acc, x -> acc + x } == intList.sum())

        println(doubleList.product())  // 24.0
        println(doubleList.foldLeft(1.0) { acc, x -> acc * x }) // 24.0
        require(doubleList.foldLeft(1.0) { acc, x -> acc * x } == doubleList.product())

        val lowerCharList = Cons('a', Cons('b', Cons('c', Cons('d', Nil))))
        val upperCharList = Cons('A', Cons('B', Cons('C', Cons('D', Nil))))

        require(lowerCharList.foldLeft(Nil) { acc: FunList<Char>, char ->
            acc.appendTail(char.toUpperCase())
        } == upperCharList)

        val sum = { acc: Int, value: Int -> acc + value }
        val product = { acc: Double, value: Double -> acc * value }
        val upper = { acc: FunList<Char>, char: Char -> acc.appendTail(char.toUpperCase()) }


        println(intList.foldLeft(0, sum)) // 10
        println(doubleList.foldLeft(1.0, product)) // 24.0
        println(lowerCharList.foldLeft(Nil, upper)) // Cons('A', Cons('B', Cons('C', Cons('D', Nil))))

        println(intList.zip(lowerCharList))   // Cons((1,a), Cons((2,b), Cons((3,c), Cons((4,d), Nil))))

        val add3 = intList.map { it + 3 }
        val filterEven = add3.filter { it % 2 == 0 }
        val notChain = filterEven.sum()

        val chain = intList.map { it + 3 }
            .filter { it % 2 == 0 }
            .sum()

        require(chain == notChain)

        require(intList.toFunStream().sum() == 10)
    }
}
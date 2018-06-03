package fp.kotlin.example.chapter06

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        val const1 = Const(5.0)
        val const2 = Const(10.0)
        val sum = Sum(const1, const2)
        val notANumber = NotANumber

        println(eval(const1))     //5.0
        println(eval(const2))     //10.0
        println(eval(sum))        //15.0
        println(eval(notANumber)) //Nan

        val some = Some()
        val some2 = Some2()

        sayEq(5, 10, some)      //false
        sayEq(5, 10, some2)     //false

        val list = listOf(1,2,3,4,5)

        list.minBy { it }
    }

    private fun eval(expr: Expr): Double = when (expr) {
        is Const -> expr.number
        is Sum -> eval(expr.e1) + eval(expr.e2)
        NotANumber -> Double.NaN
    // the `else` clause is not required because we've covered all the cases
    }

    class Some : Eq<Int> {
        override fun equal(x: Int, y: Int): Boolean = x == y
        override fun notEqual(x: Int, y: Int): Boolean = x != y
    }

    class Some2: Eq<Int> {
        override fun equal(x: Int, y: Int): Boolean = x == y
        override fun notEqual(x: Int, y: Int): Boolean = x != y
    }

    fun Eq<Int>.equal(x: Int, y: Int): Boolean = x == y

    private fun <T> sayEq(x: T, y: T, eq: Eq<T>) {
        println(eq.equal(x, y))
    }

//    interface IntEq: Eq<Int> {
//        override fun equal(x: Int, y: Int): Boolean = x == y
//        override fun notEqual(x: Int, y: Int): Boolean = x != y
//    }
}
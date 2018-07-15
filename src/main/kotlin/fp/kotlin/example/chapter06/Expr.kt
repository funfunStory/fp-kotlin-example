package fp.kotlin.example.chapter06

sealed class Expr
data class Const(val number: Double) : Expr()
data class Sum(val e1: Expr, val e2: Expr) : Expr()
object NotANumber : Expr()

fun getSum(p1: Double, p2: Double): Sum {
    return Sum(Const(p1), Const(p2))
}
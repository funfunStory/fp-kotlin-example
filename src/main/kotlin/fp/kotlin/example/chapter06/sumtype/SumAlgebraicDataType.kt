package fp.kotlin.example.chapter06.sumtype

// Sum ADT Example
sealed class Shape
data class Circle(val name: String, val x: Float, val y: Float, val radius: Float): Shape()
data class Square(val name: String, val x: Float, val y: Float, val length: Float): Shape()
data class Line(val name: String, val x1: Float, val y1: Float, val x2: Float, val y2: Float): Shape()

fun main(args: Array<String>) {
    println(getGirthLength(Circle("원", 1.0f, 1.0f, 1.0f)))         // 6.283185307179586
    println(getGirthLength(Square("정사각형", 1.0f, 1.0f, 1.0f)))     // 4.0
    println(getGirthLength(Line("직선", 1.0f, 1.0f, 4.0f, 5.0f))) // 5.0
}

private fun getGirthLength(shape: Shape): Double = when (shape) {
    is Circle -> 2 * Math.PI * shape.radius
    is Square -> 4 * shape.length.toDouble()
    is Line -> {
        val x2 = Math.pow(shape.x2 - shape.x1.toDouble(), 2.0)
        val y2 = Math.pow(shape.y2 - shape.y1.toDouble(), 2.0)
        Math.sqrt(x2 + y2)
    }
}
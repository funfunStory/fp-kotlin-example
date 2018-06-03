package fp.kotlin.example.chapter06

/* Product ADT Example1
class Circle(name: String, x: Float, y: Float, radius: Float)
 */

/* Product ADT Example2
open class Shape(name: String, x: Float, y: Float)
class Circle(name: String, x: Float, y: Float, radius: Float): Shape(name, x, y)
class Square(name: String, x: Float, y: Float, length: Float): Shape(name, x, y)
 */

/* Product ADT Example3
open class Shape(name: String)
class Circle(name: String, x: Float, y: Float, radius: Float): Shape(name)
class Square(name: String, x: Float, y: Float, length: Float): Shape(name)
class Line(name: String, x1: Float, y1: Float, x2: Float, y2: Float): Shape(name)
 */

// Sum ADT Example
sealed class Shape
data class Circle(var name: String, val x: Float, val y: Float, val radius: Float): Shape()
data class Square(val name: String, val x: Float, val y: Float, val length: Float): Shape()
data class Line(val name: String, val x1: Float, val y1: Float, val x2: Float, val y2: Float): Shape()

fun main(args: Array<String>) {
    println(getGirthLength(Circle("원", 1.0f, 1.0f, 1.0f)))         // 6.283185307179586
    println(getGirthLength(Square("정사각형", 1.0f, 1.0f, 1.0f)))       // 4
    println(getGirthLength(Line("직선", 1.0f, 1.0f, 4.0f, 5.0f)))   // 5
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
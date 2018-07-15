package fp.kotlin.example.chapter06

enum class Color(val rgb: Int) {
    RED(0xFF0000),
    GREEN(0x00FF00),
    BLUE(0x0000FF)
}

fun getRed(): Color {
    return Color.RED
}

// compile error
//fun getRed(): Color.RED {
//    return Color.RED
//}
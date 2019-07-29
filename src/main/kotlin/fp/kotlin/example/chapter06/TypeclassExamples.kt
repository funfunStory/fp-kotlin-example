package fp.kotlin.example.chapter06

interface Eq<in T> {
    fun equal(other: T): Boolean = this == other
    fun notEqual(other: T): Boolean = this != other
}

interface Print {
    fun print()
}

interface Ord<in T>: Eq<T> {
    fun compare(other: T): Int
}

sealed class TrafficLight: Eq<TrafficLight>, Print
object Red: TrafficLight() {
    override fun print() = print("Red")
}
object Yellow: TrafficLight() {
    override fun print() = print("Yellow")
}
object Green: TrafficLight() {
    override fun print() = print("Green")
}

sealed class DayOfWeek(private val ord: Int): Ord<DayOfWeek> {
    override fun compare(other: DayOfWeek): Int = when {
        this.ord > other.ord -> 1
        this.ord < other.ord -> -1
        else -> 0
    }
}
object Mon: DayOfWeek(0)
object Tue: DayOfWeek(1)
object Wen: DayOfWeek(2)
object Thu: DayOfWeek(3)
object Fri: DayOfWeek(4)
object Sat: DayOfWeek(5)
object Sun: DayOfWeek(6)


fun main() {
    println(Red.equal(Yellow))     // false
    println(Red.notEqual(Yellow))  // true

    Red.print()     // Red
    Yellow.print()  // Yellow
    Green.print()   // Green

    println(Mon.compare(Tue))   // 0
    println(Wen.equal(Thu))     // false
}


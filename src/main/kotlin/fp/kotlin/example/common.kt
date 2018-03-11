package fp.kotlin.example

fun List<Int>.head() = first()
fun List<Int>.tail() = drop(1)

fun String.head() = first()
fun String.tail() = drop(1)

fun <T> Sequence<T>.head() = first()
fun <T> Sequence<T>.tail() = drop(1)

fun <T> Set<T>.head() = first()
fun <T> Set<T>.tail() = drop(1).toSet()

fun <T> Collection<T>.head() = first()
fun <T> Collection<T>.tail() = drop(1)

operator fun <T> Sequence<T>.plus(other: () -> Sequence<T>) = object : Sequence<T> {
    private val thisIterator: Iterator<T> by lazy { this@plus.iterator() }
    private val otherIterator: Iterator<T> by lazy { other().iterator() }
    override fun iterator() = object : Iterator<T> {
        override fun next(): T =
                if (thisIterator.hasNext())
                    thisIterator.next()
                else
                    otherIterator.next()

        override fun hasNext(): Boolean = thisIterator.hasNext() || otherIterator.hasNext()
    }
}
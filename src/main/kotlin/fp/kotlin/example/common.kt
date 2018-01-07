package fp.kotlin.example

fun List<Int>.head() = first()
fun List<Int>.tail() = drop(1)

fun String.head() = first()
fun String.tail() = drop(1)

fun Sequence<Int>.head() = first()
fun Sequence<Int>.tail() = drop(1)

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
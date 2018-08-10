package fp.kotlin.example.chapter07

interface Functor<out A> {
    fun <B> fmap(transform: (A) -> B): Functor<B>
}
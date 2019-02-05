package fp.kotlin.example.chapter09

interface Foldable<out A> {

    fun <B> foldLeft(acc: B, f: (B, A) -> B): B

    fun <B> foldMap(f: (A) -> B, m: Monoid<B>): B = foldLeft(m.mempty()) { b , a -> m.mappend(b, f(a)) }

}
package fp.kotlin.example.chapter04

fun main(args: Array<String>) {

}

interface SelectListener {
    fun onSelectedItem(item: Item)
}

// 뷰 클래스
class SomeViewClass() : SelectListener {
    val adatper = ItemAdatper(this)
    // . . .
    override fun onSelectedItem(item: Item) {
        // 아이템 소비
    }
    // . . .
}

data class Item(val name: String, val desc: String)

// 아이템들을 관리하는 어댑터
class ItemAdatper(private val selectListener: SelectListener) {

    private val items: List<Item> = listOf(Item("Kotlin", "Kotlin desc"),
        Item("FP", "FP desc"))

    private val view: View = TODO()

    // . . .
    // 아이템을 그리는 함수
    fun draw(position: Int) {
        val item = items[position]
        // . . .
        // 아이템이 클릭되면 콜백리스너를 통해 뷰로 아이템을 전달
        view.setOnClickListener {
            selectListener.onSelectedItem(item)
        }
        // . . .
    }
}

// Android UI 의 View를 사용하기 위해 임시로 생서해놓은 View class 실제코드와는 다르지만 4장을 이해하는데 View의 실제 코드를 이해할 필요는 없다.
class View {
    fun setOnClickListener(function: (View) -> Unit) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
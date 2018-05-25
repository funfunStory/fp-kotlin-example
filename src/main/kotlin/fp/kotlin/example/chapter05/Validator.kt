package fp.kotlin.example.chapter05

fun main(args: Array<String>) {

    val games = listOf(
            Game(1, "mario", "nintendo", "action", 10000),
            Game(2, "wow", "blizzard", "RPG", 20000),
            Game(3, "lol", "riot", "simulation", -1000),
            Game(-1, "diablo", "blizzard", "RPG", 15000),
            Game(4, "overwatch", "", "fps", 0))

    games.filter { it.isValid() }

}

data class Game(
        val id: Long,
        val alias: String,
        val company: String,
        val genre: String,
        val price: Int): Validator{

    override fun isValid(): Boolean {
        if(id <= 0) return false
        if(alias.isEmpty()) return false
        if(company.isEmpty()) return false
        if(genre.isEmpty()) return false
        if(price < 0) return false

        return true
    }
}

interface Validator{
    fun isValid(): Boolean
}
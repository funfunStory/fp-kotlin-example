package fp.kotlin.example.chapter05

fun main() {

    val games = listOf(
            Game(1, "mario", Genre.ACTION, 10000, 2527429927221),
            Game(2, "wow", Genre.RPG, 20000, 3527429927221),
            Game(3, "lol", Genre.SIMULATION, -1000, 527429927221),
            Game(-1, "diablo", Genre.RPG, 15000, 1327429927221),
            Game(4, "overwatch", Genre.FPS, 0,1827429927221),
            Game(5, "starcraft", Genre.SIMULATION, 2000, 1827429927521))

    games.filter { it.isValid() }

}

data class Game(
        val id: Long,
        val alias: String,
        val genre: Genre,
        val price: Int,
        val expiredAt: Long): Validator{

    override fun isValid(): Boolean {
        if(id <= 0) return false
        if(alias.isEmpty()) return false
        if(price < 0) return false
        if(expiredAt < System.currentTimeMillis()) return false

        return true
    }
}

enum class Genre{
    ACTION, RPG, FPS, SIMULATION
}

interface Validator{
    fun isValid(): Boolean
}
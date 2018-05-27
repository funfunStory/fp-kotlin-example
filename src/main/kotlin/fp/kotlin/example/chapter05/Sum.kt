package fp.kotlin.example.chapter05

fun main(args: Array<String>) {
        val games = listOf(
                Game(1, "mario", "nintendo", "action", 10000),
                Game(2, "wow", "blizzard", "RPG", 20000),
                Game(3, "lol", "riot", "simulation", -1000),
                Game(-1, "diablo", "blizzard", "RPG", 15000),
                Game(4, "overwatch", "", "fps", 0))

        games.asSequence()
                .filter { it.isValid() }
                .filter { it.genre == "RPG" }
                .fold(0){acc, game -> acc + game.price }
}
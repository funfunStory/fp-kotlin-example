package fp.kotlin.example.chapter05

fun main(args: Array<String>) {

        val games = listOf(
                Game(1, "mario", Genre.ACTION, 10000, 2527429927221),
                Game(2, "wow", Genre.RPG, 20000, 3527429927221),
                Game(3, "lol", Genre.SIMULATION, -1000, 527429927221),
                Game(-1, "diablo", Genre.RPG, 15000, 1327429927221),
                Game(4, "overwatch", Genre.FPS, 0,1827429927221),
                Game(5, "starcraft", Genre.SIMULATION, 2000, 1827429927521))

        games.asSequence()
                .filter { it.isValid() }
                .filter { it.genre == Genre.RPG }
                .fold(0){acc, game -> acc + game.price }
}
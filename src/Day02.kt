class Bag(val red: Long, val green: Long, val blue: Long) {
    fun isPossible(bag: Bag) = bag.red >= red && bag.green >= green && bag.blue >= blue
    fun power() = red * green * blue

    companion object {
        fun fromMap(map: Map<String, Long>) = Bag(
            map.getOrDefault("red", 0L),
            map.getOrDefault("green", 0L),
            map.getOrDefault("blue", 0L)
        )

        fun parse(str: String): Bag {
            val map = str.split(',').associate {
                val (count, color) = it.trim().split(' ')
                color to count.toLong()
            }
            return fromMap(map)
        }
    }

}
class Game(val id: Int, val moves: List<Bag>) {
    fun isPossible(bag: Bag) = moves.all { it.isPossible(bag) }
    fun minBag() = Bag(moves.maxOf(Bag::red), moves.maxOf(Bag::green), moves.maxOf(Bag::blue))

    companion object {
        fun parse(str: String) : Game {
            val id = str.substringBefore(":").substringAfter("Game").trim().toInt()
            val moves = str.substringAfter(":").split(";")
            return Game(id, moves.map(Bag::parse))
        }
    }
}

fun main() {
    val gameData = Bag(12, 13, 14)

    fun part1(input: List<String>) = input.map(Game::parse).filter { it.isPossible(gameData) }.sumOf(Game::id)
    fun part2(input: List<String>) = input.sumOf { Game.parse(it).minBag().power() }

    val input = readInput("day2")
    part1(input).println()
    part2(input).println()
}

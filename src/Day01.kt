import java.lang.Exception

enum class Digits {
    zero, one, two, three, four, five, six, seven, eight, nine;
    override fun toString() = ordinal.toString()
    companion object {
        fun fromString(str: String) =
            Digits.entries.find { it.name == str || it.ordinal.toString() == str } ?: throw Exception()
    }
}

fun main() {
    fun getPart1Number(str: String) = "${str.find(Char::isDigit)}${str.findLast(Char::isDigit)}".toInt()

    fun Regex.findLast(str: String) = str.indices.reversed().firstNotNullOfOrNull { this.find(str, it) }

    val digitRegex = Regex(Digits.entries.joinToString("|") { "${it.name}|${it.ordinal}" })
    fun getPart2Number(str: String): Int {
        val digits = listOfNotNull(digitRegex.find(str)?.value, digitRegex.findLast(str)?.value)
            .map { Digits.fromString(it) }

        return "${digits.first()}${digits.last()}".toInt()
    }

    fun part1(input: List<String>) = input.sumOf(::getPart1Number)
    fun part2(input: List<String>) = input.sumOf(::getPart2Number)

    val input = readInput("day1")
    part1(input).println()
    part2(input).println()
}

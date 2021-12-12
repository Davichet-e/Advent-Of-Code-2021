import java.io.File
import kotlin.test.assertEquals

object Day2 {
    fun part1(file: String): Int {
        val position = File(file).readLines().fold(Pair(0, 0)) { acc, value ->
            val splitted = value.split(" ")
            val quantity = splitted[1].toInt()
            when (splitted.first()) {
                "forward" -> Pair(acc.first + quantity, acc.second)
                "down" -> Pair(acc.first, acc.second + quantity)
                "up" -> Pair(acc.first, acc.second - quantity)
                else -> throw Exception("Unreachable code")
            }
        }
        return position.first * position.second
    }

    fun part2(file: String): Int {
        val position = File(file).readLines().fold(Triple(0, 0, 0)) { acc, value ->
            val splitted = value.split(" ")
            val quantity = splitted[1].toInt()
            when (splitted.first()) {
                "forward" -> acc.copy(first = acc.first + quantity, second = acc.second + acc.third * quantity)
                "down" -> acc.copy(third = acc.third + quantity)
                "up" -> acc.copy(third = acc.third - quantity)
                else -> throw Exception("Unreachable code")
            }
        }
        return position.first * position.second
    }
}

fun main() {
    val file = "inputs/day2"

    val part1Answer = Day2.part1(file)
    assertEquals(part1Answer, 1636725)

    val part2Answer = Day2.part2(file)
    assertEquals(part2Answer, 1872757425)

    println("Day 2 answers:\nPart 1: $part1Answer\nPart 2: $part2Answer")
}
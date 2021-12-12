import java.io.File
import kotlin.math.absoluteValue
import kotlin.test.assertEquals

object Day7 {
    fun part1(file: String): Int = solve(file) { a, b -> (a - b).absoluteValue }

    fun part2(file: String): Int = solve(file, Day7::calculateCost)

    private fun solve(file: String, calculateFuel: (Int, Int) -> Int): Int {
        val positions = File(file).readLines()[0].split(",").map { it.toInt() }
        val max = positions.maxOf { it }

        return (0..max).minOf { position ->
            positions.sumOf { calculateFuel(it, position) }
        }
    }

    private fun calculateCost(from: Int, to: Int): Int {
        val n = (from - to).absoluteValue
        return (n * (n + 1)) / 2
    }
}

fun main() {
    val file = "inputs/day7"

    val part1Answer = Day7.part1(file)
    assertEquals(part1Answer, 348996)

    val part2Answer = Day7.part2(file)
    assertEquals(part2Answer, 98231647)

    println("Day 7 answers:\nPart 1: $part1Answer\nPart 2: $part2Answer")
}
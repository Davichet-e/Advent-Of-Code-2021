import java.io.File
import kotlin.test.assertEquals

object Day6 {

    fun solve(file: String, iterations: Int): Long {
        val initialCount = File(file).readLines()[0].split(",").groupingBy { it.toInt() }.eachCount()
        val counter = mutableMapOf<Int, Long>()
        for (i in 0..9) {
            val initialValue = initialCount[i]
            counter[i] = initialValue?.toLong() ?: 0
        }

        for (_i in 0 until iterations) {
            for (j in 0..9) {
                val previousValue = counter[j]!!
                counter[j] = 0
                if (j == 0) {
                    counter.merge(7, previousValue, Long::plus)
                    counter.merge(9, previousValue, Long::plus)
                } else {
                    counter.merge(j - 1, previousValue, Long::plus)
                }
            }

        }
        return counter.values.sumOf { it }
    }
}

fun main() {
    val file = "inputs/day6"

    val part1Answer = Day6.solve(file, 80)
    assertEquals(part1Answer, 394994)

    val part2Answer = Day6.solve(file, 256)
    assertEquals(part2Answer, 1765974267455)

    println("Day 6 answers:\nPart 1: $part1Answer\nPart 2: $part2Answer")
}
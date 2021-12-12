import java.io.File
import kotlin.test.assertEquals

object Day1 {
    private fun <T> calculateNOfMeasurementsAreLarger(list: List<T>, parseValue: (T) -> Int): Int =
        list.fold(
            Pair(-1, Int.MIN_VALUE)
        ) { acc, value ->
            val measurement = parseValue(value)
            if (measurement > acc.second)
                Pair(acc.first + 1, measurement)
            else
                Pair(acc.first, measurement)

        }.first

    fun part1(a: String): Int = calculateNOfMeasurementsAreLarger(File(a).readLines(), String::toInt)

    fun part2(a: String): Int = calculateNOfMeasurementsAreLarger(
        File(a).readLines().windowed(size = 3, step = 1),
        ::parseMeasurement
    )
}
private fun parseMeasurement(list: List<String>): Int = list.sumOf { it.toInt() }

fun main() {
    val file = "inputs/day1"

    val part1Answer = Day1.part1(file)
    assertEquals(part1Answer, 1154)

    val part2Answer = Day1.part2(file)
    assertEquals(part2Answer, 1127)

    println("Day 1 answers:\nPart 1: $part1Answer\nPart 2: $part2Answer")
}
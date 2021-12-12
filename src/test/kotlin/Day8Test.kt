import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day8Test {
    @Test
    fun part1() {
        val file = "examples/day8"
        val result = Day8.part1(file)
        assertEquals(26, result)
    }

    @Test
    fun part2() {
        val file = "examples/day8"
        val result = Day8.part2(file)
        assertEquals(61229, result)
    }

    @Test
    fun customDisplay() {
        val inputs = """
        |acedgfb: 8
        |cdfbe: 5
        |gcdfa: 2
        |fbcad: 3
        |dab: 7
        |cefabd: 9
        |cdfgeb: 6
        |eafb: 4
        |cagedb: 0
        |ab: 1
""".trimIndent().lines().forEach { line ->
            val splitted = line.split(": ")

            assertEquals(Day8.sevenDisplayToNumber(Day8.customDisplay(splitted[0], "deafgbc")), splitted[1].toInt())
        }

    }
}
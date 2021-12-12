import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day11Test {
    @Test
    fun part1() {
        val file = "examples/day11"
        val result = Day11.part1(file)
        assertEquals(1656, result)
    }

    @Test
    fun part2() {
        val file = "examples/day11"
        val result = Day11.part2(file)
        assertEquals(195, result)
    }
}
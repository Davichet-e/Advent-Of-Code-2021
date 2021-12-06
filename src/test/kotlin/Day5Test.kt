import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day5Test {
    @Test
    fun part1() {
        val file = "examples/day5"
        val result = Day5.solve(file, false)
        assertEquals(5, result)
    }

    @Test
    fun part2() {
        val file = "examples/day5"
        val result = Day5.solve(file, true)
        assertEquals(12, result)
    }
}
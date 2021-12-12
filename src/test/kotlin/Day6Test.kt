import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day6Test {
    @Test
    fun part1() {
        val file = "examples/day6"
        val result = Day6.solve(file, 80)
        assertEquals(5934, result)
    }

    @Test
    fun part2() {
        val file = "examples/day6"
        val result = Day6.solve(file, 256)
        assertEquals(26984457539, result)
    }
}
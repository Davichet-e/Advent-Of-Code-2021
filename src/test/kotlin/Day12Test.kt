import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day12Test {
    @Test
    fun part1() {
        val file = "examples/day12"
        val result = Day12.part1(file)
        assertEquals(226, result)
    }

    @Test
    fun part2() {
        val file = "examples/day12"
        val result = Day12.part2(file)
        assertEquals(3509, result)
    }
}
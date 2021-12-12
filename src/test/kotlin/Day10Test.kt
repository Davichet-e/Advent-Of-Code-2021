import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day10Test {
    @Test
    fun part1() {
        val file = "examples/day10"
        val result = Day10.part1(file)
        assertEquals(26397, result)
    }

    @Test
    fun part2() {
        val file = "examples/day10"
        val result = Day10.part2(file)
        assertEquals(288957, result)
    }
}
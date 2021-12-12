import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day7Test {
    @Test
    fun part1() {
        val file = "examples/day7"
        val result = Day7.part1(file)
        assertEquals(37, result)
    }

    @Test
    fun part2() {
        val file = "examples/day7"
        val result = Day7.part2(file)
        assertEquals(168, result)
    }
}
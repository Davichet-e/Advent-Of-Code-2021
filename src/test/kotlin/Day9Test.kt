import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day9Test {
    @Test
    fun part1() {
        val file = "examples/day9"
        val result = Day9.part1(file)
        assertEquals(15, result)
    }

    @Test
    fun part2() {
        val file = "examples/day9"
        val result = Day9.part2(file)
        assertEquals(1134, result)
    }
}
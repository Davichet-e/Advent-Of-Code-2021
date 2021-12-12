import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day1Test {
    @Test
    fun part1() {
        val file = "examples/day1"
        val result = Day1.part1(file)
        assertEquals(result, 7)
    }

    @Test
    fun part2() {
        val file = "examples/day1"
        val result = Day1.part2(file)
        assertEquals(result, 5)
    }
}
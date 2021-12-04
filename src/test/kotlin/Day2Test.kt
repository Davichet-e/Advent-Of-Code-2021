import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day2Test {
    @Test
    fun part1() {
        val file = "examples/day2"
        val result = Day2.part1(file)
        assertEquals(result, 150)
    }

    @Test
    fun part2() {
        val file = "examples/day2"
        val result = Day2.part2(file)
        assertEquals(result, 900)
    }
}
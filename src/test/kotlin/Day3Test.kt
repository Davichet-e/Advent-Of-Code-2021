import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day3Test {
    @Test
    fun part1() {
        val file = "examples/day3"
        val result = Day3.part1(file)
        assertEquals(198, result)
    }

    @Test
    fun part2() {
        val file = "examples/day3"
        val result = Day3.part2(file)
        assertEquals(230, result)
    }
}
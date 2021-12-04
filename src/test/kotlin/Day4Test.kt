import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day4Test {
    @Test
    fun part1() {
        val file = "examples/day4"
        val result = Day4.part1(file)
        assertEquals(4512, result)
    }

    @Test
    fun part2() {
        val file = "examples/day4"
        val result = Day4.part2(file)
        assertEquals(1924, result)
    }
}
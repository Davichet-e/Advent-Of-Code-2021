import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day1Test {
    @Test
    fun part1() {
        val file = "inputs/day1"
        val result = Day1.part1(file)
        assertEquals(result, 1154)
    }

    @Test
    fun part2() {
        val file = "inputs/day1"
        val result = Day1.part2(file)
        assertEquals(result, 1127)
    }
}
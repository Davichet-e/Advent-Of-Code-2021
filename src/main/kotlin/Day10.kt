import java.io.File
import kotlin.test.assertEquals


object Day10 {
    private val scoresP1 = mapOf(')' to 3, ']' to 57, '}' to 1197, '>' to 25137)
    private val scoresP2 = listOf('\n', '(', '[', '{', '<')
    private val closeCounterPart = mapOf(
        Pair('(', ')'),
        Pair('[', ']'),
        Pair('{', '}'),
        Pair('<', '>')
    )

    fun part1(file: String): Int =
        File(file).readLines().map { it.toCharArray() }.fold(0) { acc, chars ->
            val queue: ArrayDeque<Char> = ArrayDeque()
            val foundChar = chars.find { char ->
                if (char == '(' || char == '[' || char == '<' || char == '{') {
                    queue.addLast(char)
                    false
                } else {
                    val last = queue.removeLastOrNull()
                    if (last == null) {
                        true
                    } else {
                        closeCounterPart[last] != char
                    }
                }
            }
            if (foundChar != null)
                acc + scoresP1[foundChar]!!
            else acc
        }

    private fun calculateScore(remaining: Iterable<Char>): Long =
        remaining.reversed().fold(0L) { acc, c -> acc * 5 + scoresP2.indexOf(c) }

    fun part2(file: String): Long {
        val scores = File(file).readLines().map { it.toCharArray() }.mapNotNull { chars ->
            val queue: ArrayDeque<Char> = ArrayDeque()
            val foundChar = chars.find { char ->
                if (char == '(' || char == '[' || char == '<' || char == '{') {
                    queue.addLast(char)
                    false
                } else {
                    val last = queue.removeLastOrNull()
                    if (last == null) {
                        true
                    } else {
                        closeCounterPart[last]!! != char
                    }
                }
            }
            if (foundChar == null) {
                calculateScore(queue)
            } else null
        }.sorted()

        return scores[scores.size / 2]
    }
}

fun main() {
    val file = "inputs/day10"

    val part1Answer = Day10.part1(file)
    assertEquals(part1Answer, 370407)

    val part2Answer = Day10.part2(file)
    assertEquals(part2Answer, 3249889609)

    println("Day 10 answers:\nPart 1: $part1Answer\nPart 2: $part2Answer")
}
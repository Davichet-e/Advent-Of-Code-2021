import java.io.File
import java.util.*
import kotlin.math.absoluteValue
import kotlin.test.assertEquals

object Day5 {
    fun solve(file: String, isPart2: Boolean): Int {
        val lines = File(file).readLines().map { line ->
            val pattern = "(\\d{1,3}),(\\d{1,3}) -> (\\d{1,3}),(\\d{1,3})"
            val groups = Regex(pattern).find(line)!!.groups.drop(1).map {
                    it!!.value.toInt()
                }
            Line(
                groups[0], groups[1], groups[2], groups[3]
            )
        }

        val collides: MutableMap<Pair<Int, Int>, Boolean> = lines.fold(mutableMapOf()) { acc, value ->
            if (value.equalX) {
                for (i in value.range.get()) {
                    acc.compute(Pair(value.x1, i)) { _, v ->
                        v != null
                    }
                }
            } else if (value.equalY) {
                for (i in value.range.get()) {
                    acc.compute(Pair(i, value.y1)) { _, v ->
                        v != null
                    }
                }
            } else if (isPart2 && value.isDiagonal) {
                val operatorY = { n: Int -> if (value.y1 < value.y2) value.y1 + n else value.y1 - n }
                val operatorX = { n: Int -> if (value.x1 < value.x2) value.x1 + n else value.x1 - n }
                for (i in 0..value.diagonalLineLength.get()) {
                    acc.compute(Pair(operatorX(i), operatorY(i))) { _, v ->
                        v != null
                    }
                }
            }
            acc
        }
        return collides.values.count { it }
    }
}

data class Line(val x1: Int, val y1: Int, val x2: Int, val y2: Int) {
    override fun toString(): String {
        return "($x1, $y1) -> ($x2, $y2)"
    }

    val equalX = x1 == x2

    val equalY = y1 == y2

    val isDiagonal: Boolean = (x1 - x2).absoluteValue == (y1 - y2).absoluteValue

    val range: Optional<IntRange> =
        Optional.ofNullable(
            if (equalX) {
                if (y1 < y2) y1..y2
                else y2..y1
            } else if (equalY) {
                if (x1 < x2) x1..x2
                else x2..x1
            } else {
                null
            }
        )

    val diagonalLineLength: Optional<Int> = Optional.ofNullable(
        if (isDiagonal) (x1 - x2).absoluteValue
        else null
    )
}

fun main() {
    val file = "inputs/day5"

    val part1Answer = Day5.solve(file, false)
    assertEquals(part1Answer, 4728)

    val part2Answer = Day5.solve(file, true)
    assertEquals(part2Answer, 17717)

    println("Day 5 answers:\nPart 1: $part1Answer\nPart 2: $part2Answer")
}
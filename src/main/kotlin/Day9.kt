import java.io.File
import kotlin.test.assertEquals

object Day9 {
    fun part1(file: String): Int =
        HeightMap(
            File(file).readLines()
                .map { line -> line.toCharArray().map { it.digitToInt() } })
            .findLowPoints()
            .fold(0) { acc, value -> acc + value + 1 }

    fun part2(file: String): Int {
        val heightMap = HeightMap(
            File(file).readLines()
                .map { line -> line.toCharArray().map { it.digitToInt() } })
        heightMap.fillBasins()

        return heightMap.findBasinsSize().values.sortedDescending().take(3).fold(1, Int::times)
    }
}

class HeightMap(private val points: List<List<Int>>) {
    private val basinsOfPoints = mutableMapOf<Pair<Int, Int>, Int>()
    private var currentBasinIndex: Int = 0

    fun findBasinsSize(): Map<Int, Int> =
        basinsOfPoints.values.groupingBy { it }.eachCount()

    private fun findErroneusBasis(basisIndex: Int): List<Pair<Int, Int>> {
        return basinsOfPoints.entries.filter { it.value == basisIndex }.map { it.key }
    }

    fun fillBasins() {
        points.forEachIndexed { indexY, row ->
            row.forEachIndexed { indexX, element ->
                if (element != 9) {
                    val neighbourBasin = getNeighboursPosition(indexY, indexX).mapNotNull { basinsOfPoints[it] }
                    if (neighbourBasin.isEmpty()) {
                        basinsOfPoints[Pair(indexY, indexX)] = currentBasinIndex++
                    } else {
                        if (neighbourBasin.size > 1 && neighbourBasin[0] != neighbourBasin[1]) {
                            findErroneusBasis(neighbourBasin[1]).forEach { position ->
                                basinsOfPoints.replace(position, neighbourBasin[0])
                            }
                        }
                        basinsOfPoints[Pair(indexY, indexX)] = neighbourBasin[0]
                    }
                }
            }
        }
    }

    fun findLowPoints(): List<Int> =
        points.mapIndexed { indexY, row ->
            row.filterIndexed { indexX, n ->
                n < getNeighboursPosition(
                    indexY,
                    indexX
                ).minOf { (y, x) -> points[y][x] }
            }
        }.flatten()

    private fun getNeighboursPosition(y: Int, x: Int): List<Pair<Int, Int>> =
        listOf(
            Pair(y, x - 1),
            Pair(y + 1, x),
            Pair(y, x + 1),
            Pair(y - 1, x)
        ).filter { isValidPosition(it) }

    private fun isValidPosition(position: Pair<Int, Int>): Boolean {
        val (y, x) = position
        return x >= 0 && y >= 0 && x < points[0].size && y < points.size
    }
}

fun main() {
    val file = "inputs/day9"

    val part1Answer = Day9.part1(file)
    assertEquals(part1Answer, 489)

    val part2Answer = Day9.part2(file)
    assertEquals(part2Answer, 1056330)

    println("Day 9 answers:\nPart 1: $part1Answer\nPart 2: $part2Answer")
}
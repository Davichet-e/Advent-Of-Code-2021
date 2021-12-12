import java.io.File
import kotlin.test.assertEquals

object Day11 {
    fun part1(file: String): Int =
        Grid(File(file).readLines()).countNOfFlashLightAfterNSteps(100)

    fun part2(file: String): Int =
        Grid(File(file).readLines()).firstTimeSyncronize()
}
typealias Position = Pair<Int, Int>

class Grid(listOfString: List<String>) {
    val grid: List<List<DOctopus>>
    private val nOfElements: Int

    init {
        grid = listOfString.mapIndexed { indexY, string ->
            string.toCharArray().mapIndexed { indexX, char -> DOctopus(char.digitToInt(), indexY to indexX) }
        }
        nOfElements = grid.sumOf { it.size }
    }
    fun executeStep(nStep: Int) =
        grid.forEach { row ->
            row.forEach { dOctopus ->
                dOctopus.step(nStep, this)
            }
        }

    fun countNOfFlashLightAfterNSteps(nSteps: Int): Int {
        return (0 .. nSteps).reduce { acc, nStep ->
            executeStep(nStep)
            val count = countFlashes()
            resetFlash()
            acc + count
        }
    }


    fun firstTimeSyncronize(): Int {
        (1..Int.MAX_VALUE).forEach { nStep ->
            executeStep(nStep)
            val count = countFlashes()
            if (count == nOfElements) {
                return nStep
            }
            resetFlash()
        }
        return -1
    }

    private fun countFlashes(): Int =
        grid.sumOf { row -> row.count { it.flash } }

    private fun resetFlash() =
        grid.forEach { row ->
            row.forEach { dOctopus ->
                dOctopus.flash = false
            }
        }

    fun getNeighboursPosition(y: Int, x: Int): List<Position> = arrayOf(
        y - 1 to x,
        y - 1 to x + 1,
        y     to x + 1,
        y + 1 to x + 1,
        y + 1 to x,
        y + 1 to x - 1,
        y     to x - 1,
        y - 1 to x - 1
    ).filter { isValidPosition(it) }

    private fun isValidPosition(position: Position): Boolean {
        val (y, x) = position
        return x >= 0 && y >= 0 && x < grid[0].size && y < grid.size
    }
}

class DOctopus(private var value: Int, private val position: Position) {
    private var nStep = -1
    var flash = false
    fun step(nStep: Int, grid: Grid) {
        if (nStep == this.nStep && flash) {
            return
        }
        if (nStep != this.nStep) {
            flash = false
        }
        this.nStep = nStep
        value++

        if (value > 9) {
            flash = true
            value = 0
            grid.getNeighboursPosition(position.first, position.second).forEach { (y, x) ->
                val neighbour = grid.grid[y][x]
                if (!neighbour.flash) {
                    neighbour.step(nStep, grid)
                }
            }

        }
    }
}

fun main() {
    val file = "inputs/day11"

    val part1Answer = Day11.part1(file)
    assertEquals(part1Answer, 1599)

    val part2Answer = Day11.part2(file)
    assertEquals(part2Answer, 418)

    println("Day 11 answers:\nPart 1: $part1Answer\nPart 2: $part2Answer")
}
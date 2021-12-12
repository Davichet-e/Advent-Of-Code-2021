import java.io.File
import kotlin.test.assertEquals

object Day4 {

    private fun calculateScore(board: MutableList<MutableList<Int?>>, finalBingoNumber: Int): Int =
        board.fold(0) { acc, value -> acc + value.filterNotNull().sumOf { it } } * finalBingoNumber

    private fun createBoards(lines: List<String>): MutableList<MutableList<MutableList<Int?>>> {
        val boards: MutableList<MutableList<MutableList<Int?>>> = mutableListOf()
        lines.drop(1).forEach { it ->
            if (it.isEmpty()) {
                boards.add(mutableListOf())
            }
            else {
                boards.last().add(Regex("\\d{1,2}").findAll(it).toList().map { it.value.toInt() }
                    .toMutableList())
            }
        }
        return boards
    }

    private fun checkBingo(board: MutableList<MutableList<Int?>>): Boolean {
        for (i in board.indices) {
            if (board[i].all { it == null } || board.all { it[i] == null }) {
                return true
            }
        }
        return false
    }

    fun part1(file: String): Int {
        val lines = File(file).readLines()
        val bingoNumbers = lines[0].split(",").map { it.toInt() }
        val boards: MutableList<MutableList<MutableList<Int?>>> = createBoards(lines)

        bingoNumbers.forEach { bingoNumber ->
            boards.forEach { board ->
                board.forEach { row ->
                    row.replaceAll { if (it == bingoNumber) null else it }
                }
                if (checkBingo(board)) {
                    return calculateScore(board, bingoNumber)
                }
            }
        }
        return -1
    }

    fun part2(file: String): Int {
        val lines = File(file).readLines()
        val bingoNumbers = lines[0].split(",").map { it.toInt() }
        var boards: MutableList<MutableList<MutableList<Int?>>> = createBoards(lines)

        var lastBingoNumber = -1
        var lastBoard: MutableList<MutableList<Int?>>? = null

        bingoNumbers.forEach { bingoNumber ->
            boards = boards.filter { board ->
                board.forEach { row ->
                    row.replaceAll { if (it == bingoNumber) null else it }
                }
                if (checkBingo(board)) {
                    lastBoard = board
                    lastBingoNumber = bingoNumber
                    false
                } else {
                    true
                }
            }.toMutableList()
        }
        return calculateScore(lastBoard!!, lastBingoNumber)
    }
}

fun main() {
    val file = "inputs/day4"

    val part1Answer = Day4.part1(file)
    assertEquals(part1Answer, 34506)

    val part2Answer = Day4.part2(file)
    assertEquals(part2Answer, 7686)

    println("Day 4 answers:\nPart 1: $part1Answer\nPart 2: $part2Answer")
}
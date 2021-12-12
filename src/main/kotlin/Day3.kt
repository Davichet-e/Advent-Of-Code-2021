import java.io.File
import kotlin.test.assertEquals

object Day3 {
    val binaryOne = '1'
    val binaryZero = '0'

    fun part1(file: String): Int {
        val temp = File(file).readLines().fold(MutableList(12) { 0 }) { acc, value ->
            value.forEachIndexed { index, c ->
                acc[index] += if (c == '1') 1 else -1
            }
            acc
        }
        temp.toString().filter { it == '0' || it == '1' }

        val temp2 = temp.toString().filter { it == '0' || it == '1' }
        val temp3 = temp2.filter { it == '0' || it == '1' }
        return temp2.toInt(2) * temp3.toInt(2)
    }

    private fun countOnesAndZeroes(lines: List<String>, index: Int): Map<Char, Int> {
        return lines.map { it[index] }.groupingBy { it }.eachCount()
    }

    fun countOnes(lines: List<String>, i: Int): Int =
        lines.count { it[i] == binaryOne }

    fun countZeroes(lines: List<String>, i: Int): Int =
        lines.count { it[i] == binaryZero }

    fun botijo(entry: Map.Entry<Char, Int>): Int =
        if (entry.key == binaryOne) 1
        else -1

    fun comparator(entryA: Map.Entry<Char, Int>, entryB: Map.Entry<Char, Int>) =
        if (entryA.value == entryB.value)
            botijo(entryA)
        else entryA.value - entryB.value

    fun part2(file: String): Int {
        val linesOxygen = File(file).readLines()
        var linesCO2 = linesOxygen.toList()

        // for (i in linesOxygen[0].indices) {
        linesOxygen[0].foldIndexed(linesOxygen) { i, acc, _ ->
            val countOfOcurrencesOf1sAnd0s = countOnesAndZeroes(linesOxygen, i)

            val mostCommon =
                (countOfOcurrencesOf1sAnd0s.maxWithOrNull { entryA, entryB ->
                    comparator(entryA, entryB)
                } ?: throw Exception("Bad Input")).key

            // linesOxygen = linesOxygen.filter { it[i] == most }
            // if (linesOxygen.size == 1) break
            acc.filter { it[i] == mostCommon }
        }
            // .takeWhile { it.size > 1 }

        for (i in linesCO2[0].indices) {
            val leastMap = linesCO2.map { it[i] }.groupingBy { it }.eachCount()

            val least = leastMap.minWithOrNull { a, b ->
                if (a.value == b.value)
                    if (a.key == '0') -1
                    else 1
                else a.value - b.value
            }!!.key
            linesCO2 = linesCO2.filter { it[i] == least }
            if (linesCO2.size == 1) break
        }
        return linesOxygen[0].toInt(2) * linesCO2[0].toInt(2)
    }
}


fun main() {
    val file = "inputs/day3"

    val part1Answer = Day3.part1(file)
    assertEquals(part1Answer, 1092896)

    val part2Answer = Day3.part2(file)
    assertEquals(part2Answer, 4672151)

    println("Day 3 answers:\nPart 1: $part1Answer\nPart 2: $part2Answer")
}


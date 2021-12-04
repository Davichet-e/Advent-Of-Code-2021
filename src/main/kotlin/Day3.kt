import java.io.File
import kotlin.test.assertEquals

object Day3 {
    fun part1(file: String): Int {
        val temp = File(file).readLines().fold(MutableList(12) { 0 }) { acc, value ->
            value.forEachIndexed() { index, c ->
                run {
                    acc[index] += if (c == '1') 1 else -1
                }
            }
            acc
        
        }
        val temp2 = temp.map {if (it > 0) 1 else 0}.joinToString(separator = "")
        val temp3 = temp2.map { if (it == '1') 0 else 1 }.joinToString(separator = "")
        return temp2.toInt(2) * temp3.toInt(2)
    }

    fun part2(file: String): Int {
        var linesOxygen = File(file).readLines()
        var linesCO2 = linesOxygen.toList()

        for(i in linesOxygen[0].indices) {
            val mostMap = linesOxygen.map { it[i] }.groupingBy { it }.eachCount()

            val most =
                mostMap.maxWithOrNull { a, b ->
                    if (a.value == b.value)
                        if (a.key == '1') 1
                        else -1
                    else a.value - b.value
                }!!.key
            linesOxygen = linesOxygen.filter { it[i] == most }
            if (linesOxygen.size == 1) break
        }

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


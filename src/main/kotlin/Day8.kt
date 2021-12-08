import java.io.File

/// https://stackoverflow.com/a/66851564
fun String.permute(result: String = ""): List<String> =
    if (isEmpty()) listOf(result) else flatMapIndexed { i, c -> removeRange(i, i + 1).permute(result + c) }


object Day8 {
    fun part1(file: String): Int = File(file).readLines().sumOf {
        Output(
            it.split(" | ")[1].split(" ")
        ).countEasyDigits()
    }

    fun part2(file: String): Int {
        val permutations = "abcdefg".permute()
        return File(file).readLines().sumOf {
            val splitted = it.split(" | ")
            val correctDisplay = permutations.find {
                splitted[0].split(" ").all { s -> sevenDisplayToNumber(customDisplay(s, it)) != null }
            }!!
            val output = Output(splitted[1].split(" "))
            output.calculateOutput(correctDisplay)
        }
    }

    fun sevenDisplayToNumber(sevenDisplay: List<Boolean>): Int? {
        val a = sevenDisplay[0]
        val b = sevenDisplay[1]
        val c = sevenDisplay[2]
        val d = sevenDisplay[3]
        val e = sevenDisplay[4]
        val f = sevenDisplay[5]
        val g = sevenDisplay[6]

        return if (sevenDisplay.count { it } == 7) {
            8
        } else if (a && b && c && e && f && g && !d) {
            0
        } else if (a && b && c && d && f && g && !e) {
            9
        } else if (a && c && d && e && g && !b && !f) {
            2
        } else if (a && c && d && f && g && !b && !e) {
            3
        } else if (a && b && d && e && f && g && !c) {
            6
        } else if (a && b && d && f && g && !c) {
            5
        } else if (b && c && d && f && !a && !e && !g) {
            4
        } else if (a && c && f && !b && !d && !e && !g) {
            7
        } else if (c && f && !a && !b && !d && !e && !g) {
            1
        } else {
            null
        }

    }

    fun customDisplay(input: String, configuration: String): List<Boolean> {
        return configuration.chars().toArray().map { input.contains(it.toChar()) }
    }

}


data class Output(val digits: List<String>) {

    fun calculateOutput(configuration: String): Int = digits.joinToString(separator = "") {
        Day8.sevenDisplayToNumber(Day8.customDisplay(it, configuration))!!.toString()
    }.toInt()

    fun countEasyDigits(): Int = digits.count {
        when (it.length) {
            2, 3, 4, 7 -> true
            else -> false
        }
    }
}

fun main() {
    println(Day8.part2("inputs/day8"))
}
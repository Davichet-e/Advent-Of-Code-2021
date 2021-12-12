import java.io.File

object Day12 {
    fun part1(file: String): Int {
        val adjacentMatrix = buildAdjacentMatrix(File(file).readLines())
        return countNOfPaths(adjacentMatrix, "start", setOf("start"))
    }

    fun part2(file: String): Int {
        val adjacentMatrix = buildAdjacentMatrix(File(file).readLines())
        return countNOfPathsPart2(adjacentMatrix, "start", mutableMapOf())
    }

    private fun countNOfPaths(
        adjacentMatrix: Map<String, List<String>>, node: String, visitedSmallNodes: Set<String>
    ): Int {
        return adjacentMatrix[node]!!.filter { it !in visitedSmallNodes }.fold(0) { acc, neighbourNode ->
            if (neighbourNode == "end" && visitedSmallNodes.isNotEmpty()) {
                acc + 1
            } else if (neighbourNode[0].isUpperCase()) {
                acc + countNOfPaths(adjacentMatrix, neighbourNode, visitedSmallNodes)
            } else {
                acc + countNOfPaths(adjacentMatrix, neighbourNode, visitedSmallNodes.plus(neighbourNode))
            }
        }
    }

    private fun countNOfPathsPart2(
        adjacentMatrix: Map<String, List<String>>, node: String, visitedSmallNodes: Map<String, Int>
    ): Int {
        return adjacentMatrix[node]!!.fold(0) { acc, neighbourNode ->
            if (neighbourNode == "start") {
                acc
            } else if (neighbourNode == "end") {
                acc + 1
            } else if (neighbourNode[0].isUpperCase()) {
                acc + countNOfPathsPart2(adjacentMatrix, neighbourNode, visitedSmallNodes)
            } else {
                val neighbourNodeVisitCount = visitedSmallNodes[neighbourNode]
                if (visitedSmallNodes.containsValue(2) && neighbourNodeVisitCount != null) {
                    acc
                } else {
                    acc + countNOfPathsPart2(
                        adjacentMatrix,
                        neighbourNode,
                        visitedSmallNodes.plus(arrayOf(neighbourNode to (neighbourNodeVisitCount ?: 0) + 1))
                    )
                }
            }
        }
    }

    private fun buildAdjacentMatrix(lines: List<String>): Map<String, List<String>> {
        return lines.fold(mutableMapOf()) { acc, line ->
            val splitted = line.split("-")
            acc.merge(splitted[0], listOf(splitted[1]), List<String>::plus)
            acc.merge(splitted[1], listOf(splitted[0]), List<String>::plus)
            acc
        }
    }
}

fun main() {
    println(Day12.part2("inputs/day12"))
}
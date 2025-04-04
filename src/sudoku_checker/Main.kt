package sudoku_checker

import kotlin.math.sqrt

fun main() {
    println(
        isValidSudoku(
            listOf(
                listOf('1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'X', 'G'),
                listOf('5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', '1', '2', '3', '@'),
                listOf('9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', '1', '2', '3', '4', '5', '6', '7', '8'),
                listOf('D', 'E', 'F', 'G', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C'),
                listOf('3', '4', '1', '2', '7', '8', '5', '6', 'B', 'C', '9', 'A', 'F', '#', 'D', 'E'),
                listOf('7', '8', '5', '6', 'B', 'C', '9', 'A', 'F', 'G', 'D', 'E', '3', '4', '1', '2'),
                listOf('B', 'C', '9', 'A', 'F', 'G', 'D', 'E', '3', '4', '1', '2', '7', '8', '5', '6'),
                listOf('F', 'G', 'D', 'E', '3', '4', '1', '2', '7', '8', '5', '6', 'B', 'C', '9', 'A'),
                listOf('2', '1', '4', '3', '6', '5', '8', '7', 'A', '9', 'C', 'B', 'E', 'D', '$', 'F'),
                listOf('6', '5', '8', '7', 'A', '9', 'C', 'B', 'E', 'D', 'G', 'F', '2', '1', '4', '3'),
                listOf('A', '9', 'C', 'B', 'E', 'D', 'G', 'F', '2', '1', '4', '3', '6', '5', '8', '7'),
                listOf('E', 'D', 'G', 'F', '2', '1', '4', '3', '6', '5', '8', '7', 'A', '9', 'C', 'B'),
                listOf('4', '3', '2', '1', '8', '7', '6', '5', 'C', 'B', 'A', '9', 'G', 'F', 'E', 'D'),
                listOf('8', '7', '6', '5', 'C', 'B', 'A', '9', 'G', 'F', 'E', 'D', '4', '3', '2', '1'),
                listOf('C', 'B', 'A', '9', 'G', 'F', 'E', 'D', '4', '3', '2', '1', '8', '7', '6', '5'),
                listOf('G', 'F', 'E', 'D', '4', '3', '2', '1', '8', '7', '6', '5', 'C', 'B', 'A', '9')
            )
        )
    )
}

fun isValidSudoku(input: List<List<Char>>): Boolean {
    if (!checkValidSize(input)) return false
    if (!checkValidCharacters(input)) return false
    if (checkCompleteEmpty(input)) return false
    if (!checkDuplicatesInRow(input)) return false
    if (!checkDuplicatesInColumn(input)) return false
    if (!checkDuplicatesInSubgrid(input)) return false

    return true
}

fun checkValidSize(grid: List<List<Char>>): Boolean {
    if (grid.isEmpty()) return false

    val gridSize = grid.size
    grid.forEach { row ->
        if (row.size != gridSize) return false
    }

    val subgridSize = sqrt(gridSize.toFloat())
    return subgridSize == subgridSize.toInt().toFloat()
}

fun checkValidCharacters(grid: List<List<Char>>): Boolean {
    val validCharacters = when (grid.size) {
        9 -> ('1'..'9').toList()
        16 -> ('1'..'9') + ('A'..'G')
        else -> return false
    }
    grid.forEach { row ->
        row.forEach { char ->
            if (char != '-' && char !in validCharacters) {
                return false
            }
        }
    }

    return true
}

fun checkCompleteEmpty(input: List<List<Char>>): Boolean {
    var emptyCells = 0
    input.forEach { row ->
        emptyCells += row.count { it == '-' }
    }

    return emptyCells == input.size * input.size
}

fun checkDuplicatesInRow(input: List<List<Char>>): Boolean {
    for (row in input) {
        val seenInRow: MutableList<Char> = mutableListOf()
        for (char in row) {
            if (char in seenInRow && char != '-') {
                return false
            }
            seenInRow.add(char)
        }
        seenInRow.clear()
    }
    return true
}

fun checkDuplicatesInColumn(input: List<List<Char>>): Boolean {
    for (col in 0 until input.size) {
        val seenInColumn = mutableListOf<Char>()
        for (row in 0 until input.size) {
            val char = input[row][col]
            if (char in seenInColumn && char != '-') {
                return false
            }
            seenInColumn.add(char)
        }
        seenInColumn.clear()
    }
    return true
}

fun checkDuplicatesInSubgrid(input: List<List<Char>>): Boolean {
    val subgridSize = sqrt(input.size.toFloat()).toInt()

    for (row in 0 until input.size step subgridSize.toInt()) {
        for (col in 0 until input.size step subgridSize.toInt()) {

            val seenInSubgrid = mutableListOf<Char>()
            for (i in row until row + subgridSize.toInt()) {
                for (j in col until col + subgridSize.toInt()) {
                    val char = input[i][j]
                    if (char in seenInSubgrid && char != '-') {
                        return false
                    }
                    seenInSubgrid.add(char)
                }
            }
            seenInSubgrid.clear()
        }
    }
    return true
}

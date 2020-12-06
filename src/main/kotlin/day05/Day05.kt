package day05


fun processLines1(lines: Sequence<String>): Int =
    lines.toSeatIds().maxOrNull() ?: 0

fun processLines2(lines: Sequence<String>): Int =
    lines.toSeatIds().sorted()
        .zipWithNext()
        .first { it.second != it.first + 1 }
        .first + 1

fun Sequence<String>.toSeatIds(): Sequence<Int> =
    this.map(String::toSeatId)

// inspired by https://github.com/rolf-rosenbaum/adventofcode2020/blob/main/src/main/kotlin/day5/Day5.kt

private fun String.toSeatId(): Int = substring(0, 7)
    .replace("F", "0")
    .replace("B", "1")
    .toInt(2) * 8 +
        substring(7, 10)
            .replace("L", "0")
            .replace("R", "1")
            .toInt(2)

// my original solution

val rows: IntRange =
    0..127

val columns: IntRange =
    0..7

data class Seat(
    val row: Int,
    val column: Int
) {
    val id: Int
        get() = row * 8 + column
}

fun String.toSeatOrNull(): Seat? {
    if (!"""^([FB]){7}([LR]){3}$""".toRegex().matches(this)) return null
    val row = this.substring(0 until 7).fold(rows) { acc, c -> narrow(acc, c) }.first
    val column = this.substring(7 until 10).fold(columns) { acc, c -> narrow(acc, c) }.first
    return Seat(row, column)
}

fun narrow(range: IntRange, char: Char): IntRange {
    val half = (range.last + 1 - range.first) / 2
    return when (char) {
        'F', 'L' -> range.first until range.last - half + 1
        'B', 'R' -> range.first + half..range.last
        else -> range
    }
}

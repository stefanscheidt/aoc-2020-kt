package day01


fun processLines1(lines: Sequence<String>): Int {
    return findAndMultPair(2020, lines.toListOfInt()) ?: 0
}

fun processLines2(lines: Sequence<String>): Int {
    return findAndMultTriple(2020, lines.toListOfInt()) ?: 0
}

private fun Sequence<String>.toListOfInt(): List<Int> =
    this.map(String::toInt).toList()

private tailrec fun findAndMultPair(n: Int, ints: List<Int>): Int? {
    val x = ints.firstOrNull() ?: return null
    val xs = ints.drop(1)
    return when (val y = xs.find { it == n - x }) {
        null -> findAndMultPair(n, xs)
        else -> x * y
    }
}

private tailrec fun findAndMultTriple(n: Int, ints: List<Int>): Int? {
    val x = ints.firstOrNull() ?: return null
    val xs = ints.drop(1)
    return when (val y = findAndMultPair(n - x, xs)) {
        null -> findAndMultTriple(n, xs)
        else -> x * y
    }
}
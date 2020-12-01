package day01


fun processLines1(lines: Sequence<String>): Int {
    return pairs(lines.toListOfInt()).eval()
}

fun processLines2(lines: Sequence<String>): Int {
    return tripples(lines.toListOfInt()).eval()
}

private fun Sequence<String>.toListOfInt(): List<Int> =
    this.map(String::toInt).toList()

private fun List<List<Int>>.eval(): Int =
    this
        .find { it.sum() == 2020 }
        ?.reduce { acc, i -> acc * i } ?: 0

internal fun pairs(xs: List<Int>): List<List<Int>> {
    if (xs.size < 2) return emptyList()

    val pairs = mutableListOf<List<Int>>()
    xs.forEachIndexed { index, x ->
        pairs.addAll(xs.drop(index + 1).map { y -> listOf(y, x) })
    }
    return pairs.toList()
}

internal fun tripples(xs: List<Int>): List<List<Int>> {
    if (xs.size < 3) return emptyList()

    val tripples = mutableListOf<List<Int>>()
    xs.forEachIndexed { index, x ->
        val pairs = pairs(xs.drop(index + 1))
        tripples.addAll(pairs.map { p -> p + x })
    }
    return tripples.toList()
}
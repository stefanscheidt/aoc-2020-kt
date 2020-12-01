package day01


fun processLines1(lines: Sequence<String>): Int {
    val ints = lines.toListOfInt()
    val combinations = ints.flatMap { i -> ints.map { j -> listOf(i, j) } }
    return combinations
        .find { it.sum() == 2020 }
        ?.reduce { acc, i -> acc * i } ?: 0
}

fun processLines2(lines: Sequence<String>): Int {
    val ints = lines.toListOfInt()
    val combinations = ints.flatMap { i -> ints.flatMap { j -> ints.map { k -> listOf(i, j, k) } } }
    return combinations
        .find { it.sum() == 2020 }
        ?.reduce { acc, i -> acc * i } ?: 0
}

private fun Sequence<String>.toListOfInt() = this.map(String::toInt).toList()

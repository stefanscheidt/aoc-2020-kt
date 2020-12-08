package day06


fun processText1(text: String): Int =
    text.split("\n\n")
        .map { group ->
            group.toSet().count { c -> c.isLetter() }
        }
        .sum()

fun processText2(text: String): Int =
    text.trim().split("\n\n")
        .map { group ->
            group.split("\n")
                .map(String::toSet)
                .reduce { acc, set -> acc.intersect(set) }
                .count()
        }
        .sum()
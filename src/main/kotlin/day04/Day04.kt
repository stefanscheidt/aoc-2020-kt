package day04


fun processLines1(lines: Sequence<String>): Int =
    lines.normalise()
        .map(String::toPassport)
        .count(Passport::isValid)

typealias Passport = Map<String, String>

fun Passport.isValid(): Boolean =
    this.keys.containsAll(expectedKeys)

val expectedKeys: Set<String> =
    setOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")

private fun Sequence<String>.normalise(): List<String> {
    infix fun List<String>.append(line: String) =
        if (this.last() == "")
            this.dropLast(1) + line
        else
            this.dropLast(1) + "${this.last()} $line"

    return this.fold(listOf("")) { acc, line ->
        if (line.isNotBlank()) acc append line else acc + ""
    }
}

private fun String.toPassport(): Passport =
    this.split(" ")
        .map { it.split(":") }
        .map { it[0] to it[1] }
        .toMap()

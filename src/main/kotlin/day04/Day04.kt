package day04

import kotlin.collections.Map.Entry


fun processLines1(lines: Sequence<String>): Int =
    lines.normalise()
        .map(String::toPassport)
        .count(Passport::isValid1)

fun processLines2(lines: Sequence<String>): Int =
    lines.normalise()
        .map(String::toPassport)
        .count(Passport::isValid2)

typealias Passport = Map<String, String>

fun Passport.isValid1(): Boolean =
    this.keys.containsAll(expectedKeys.keys)

fun Passport.isValid2(): Boolean =
    this.isValid1() && this.all { it.isValid2() }

private fun Entry<String, String>.isValid2(): Boolean {
    val isValid = expectedKeys[key] ?: { key == "cid" }
    return isValid(value)
}

val expectedKeys: Map<String, (String) -> Boolean> =
    mapOf(
        "byr" to { it.toInt() in 1920..2002 },
        "iyr" to { it.toInt() in 2010..2020 },
        "eyr" to { it.toInt() in 2020..2030 },
        "hgt" to ::isHgtValid,
        "hcl" to { """^#(\d|[a-f]){6}$""".toRegex().matches(it) },
        "ecl" to { it in setOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth") },
        "pid" to { """^\d{9}$""".toRegex().matches(it) }
    )

private fun isHgtValid(hgt: String): Boolean {
    val (value, unit) = """^(\d+)(cm|in)$""".toRegex().matchEntire(hgt)?.destructured ?: return false
    return if (unit == "cm") value.toInt() in 150..193 else value.toInt() in 59..76
}

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

internal fun String.toPassport(): Passport =
    this.split(" ")
        .map { it.split(":") }
        .map { it[0] to it[1] }
        .toMap()

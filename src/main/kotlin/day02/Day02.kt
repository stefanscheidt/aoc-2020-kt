package day02


fun processLines(lines: Sequence<String>, validation: Validation): Int =
    lines.map(::parseLine)
        .filter(validation)
        .count()

typealias Password = String

data class Policy(
    val a: Int,
    val b: Int,
    val char: Char
)

data class PasswordWithPolicy(
    val password: Password,
    val policy: Policy
)

typealias Validation = (PasswordWithPolicy) -> Boolean

val validation1: Validation = {
    val isChar = { c: Char -> c == it.policy.char }
    it.password.count(isChar) in (it.policy.a..it.policy.b)
}

val validation2: Validation = {
    (it.password[it.policy.a - 1] == it.policy.char) xor (it.password[it.policy.b - 1] == it.policy.char)
}

fun parseLine(line: String): PasswordWithPolicy {
    val s1 = line.split(" ")
    val s2 = s1[0].split("-")
    val a = s2[0].toInt()
    val b = s2[1].toInt()
    val char = s1[1][0]
    val password = s1[2]
    return PasswordWithPolicy(password, Policy(a, b, char))
}


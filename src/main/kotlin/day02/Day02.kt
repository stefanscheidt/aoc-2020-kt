package day02

import com.github.h0tk3y.betterParse.combinators.*
import com.github.h0tk3y.betterParse.grammar.Grammar
import com.github.h0tk3y.betterParse.grammar.parseToEnd
import com.github.h0tk3y.betterParse.lexer.literalToken
import com.github.h0tk3y.betterParse.lexer.regexToken
import com.github.h0tk3y.betterParse.parser.Parser


fun processLines(lines: Sequence<String>, validation: Validation): Int =
    lines.map { day02Grammar.parseToEnd(it) }
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
    val pos1 = it.policy.a - 1
    val pos2 = it.policy.b - 1
    val char = it.policy.char
    (it.password[pos1] == char) xor (it.password[pos2] == char)
}

val day02Grammar = object : Grammar<PasswordWithPolicy>() {
    val num by regexToken("\\d+")
    val chr by regexToken("\\w")
    val dash by literalToken("-", ignore = true)
    val colon by literalToken(":", ignore = true)
    val ws by regexToken("\\s+", ignore = true)

    val policy: Parser<Policy>
            by (num * -dash * num * -ws * chr) use {
                Policy(
                    a = t1.text.toInt(),
                    b = t2.text.toInt(),
                    char = t3.text[0]
                )
            }

    override val rootParser: Parser<PasswordWithPolicy>
            by (policy and -colon * -ws * oneOrMore(chr)) use {
                PasswordWithPolicy(
                    password = t2.joinToString(separator = "") { it.text },
                    policy = t1
                )
            }
}

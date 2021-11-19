package day02

import com.github.h0tk3y.betterParse.grammar.parseToEnd
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import java.io.File
import java.nio.charset.StandardCharsets

class Day02Test {

    @Test
    fun `process sample 1`() {
        val sample = """
            1-3 a: abcde
            1-3 b: cdefg
            2-9 c: ccccccccc
        """.trimIndent()
        val result = processLines(sample.lineSequence(), validation1)
        result shouldBe 2
    }

    @Test
    fun `process input 1`() {
        val result = File("./input/day02.txt").useLines(StandardCharsets.UTF_8) { processLines(it, validation1) }
        result shouldBe 655
    }

    @Test
    fun `process sample 2`() {
        val sample = """
            1-3 a: abcde
            1-3 b: cdefg
            2-9 c: ccccccccc
        """.trimIndent()
        val result = processLines(sample.lineSequence(), validation2)
        result shouldBe 1
    }

    @Test
    fun `process input 2`() {
        val result = File("./input/day02.txt").useLines(StandardCharsets.UTF_8) { processLines(it, validation2) }
        result shouldBe 673
    }

    @Test
    fun `parse a line`() {
        day02Grammar.parseToEnd("1-3 a: abcde") shouldBe PasswordWithPolicy("abcde", Policy(1, 3, 'a'))
    }
}

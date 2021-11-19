package day01

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import java.io.File
import java.nio.charset.StandardCharsets

val sample = """
            1721
            979
            366
            299
            675
            1456
        """.trimIndent()

class Day01Test {

    @Test
    fun `process sample 1`() {
        val result = processLines1(sample.lineSequence())
        result shouldBe 514579
    }

    @Test
    fun `process sample 2`() {
        val result = processLines2(sample.lineSequence())
        result shouldBe 241861950
    }

    @Test
    fun `process input 1`() {
        val result = File("./input/day01.txt").useLines(StandardCharsets.UTF_8, ::processLines1)
        result shouldBe 921504
    }

    @Test
    fun `process input 2`() {
        val result = File("./input/day01.txt").useLines(StandardCharsets.UTF_8, ::processLines2)
        result shouldBe 195700142
    }
}

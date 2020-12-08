package day06

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import java.io.File

val sample: String =
    """
        abc

        a
        b
        c

        ab
        ac

        a
        a
        a
        a

        b
        
    """.trimIndent()

class Day06Test {

    @Test
    fun `process sample 1`() {
        processText1(sample) shouldBe 11
    }

    @Test
    fun `process text 1`() {
        val text = File("./input/day06.txt").readText()

        processText1(text) shouldBe 6748
    }

    @Test
    fun `process sample 2`() {
        processText2(sample) shouldBe 6
    }

    @Test
    fun `process text 2`() {
        val text = File("./input/day06.txt").readText()

        processText2(text) shouldBe 3445
    }

}
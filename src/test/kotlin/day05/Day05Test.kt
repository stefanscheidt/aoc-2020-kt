package day05

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import java.io.File
import java.nio.charset.StandardCharsets

class Day05Test {

    @Test
    fun `string to seat`() {
        "FBFBBFFRLR".toSeatOrNull() shouldBe Seat(44, 5)
    }

    @Test
    fun `seat ID`() {
        Seat(44, 5).id shouldBe 357
    }

    @Test
    fun `process input 1`() {
        val result = File("./input/day05.txt").useLines(StandardCharsets.UTF_8, ::processLines1)

        result shouldBe 930
    }

    @Test
    fun `process input 2`() {
        val result = File("./input/day05.txt").useLines(StandardCharsets.UTF_8, ::processLines2)

        result shouldBe 515
    }
}

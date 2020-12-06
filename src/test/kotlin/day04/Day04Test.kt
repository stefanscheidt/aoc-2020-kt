package day04

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import java.io.File
import java.nio.charset.StandardCharsets


class Day04Test {

    @Test
    fun `process sample 1`() {
        val sample = """
            ecl:gry pid:860033327 eyr:2020 hcl:#fffffd
            byr:1937 iyr:2017 cid:147 hgt:183cm

            iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884
            hcl:#cfa07d byr:1929

            hcl:#ae17e1 iyr:2013
            eyr:2024
            ecl:brn pid:760753108 byr:1931
            hgt:179cm

            hcl:#cfa07d eyr:2025 pid:166559648
            iyr:2011 ecl:brn hgt:59in
        """.trimIndent()

        val result = processLines1(sample.lineSequence())

        result shouldBe 2
    }

    @Test
    fun `process input 1`() {
        val result = File("./input/day04.txt").useLines(StandardCharsets.UTF_8, ::processLines1)

        result shouldBe 204
    }
}


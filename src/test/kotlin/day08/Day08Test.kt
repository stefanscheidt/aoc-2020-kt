package day08

import day08.ComputerState.LOOPING
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import java.io.File


val sample = """
    nop +0
    acc +1
    jmp +4
    acc +3
    jmp -3
    acc -99
    acc +1
    jmp -4
    acc +6
    """.trimIndent().lines()

class Day08Test {

    @Test
    fun `process sample 1`() {
        val program = parseProgramm(sample)

        val result = runProgramm(program)

        result.state shouldBe LOOPING
        result.acc shouldBe 5
    }

    @Test
    fun `process input 1`() {
        val program = parseProgramm(File("./input/day08.txt").readLines())

        val result = runProgramm(program)

        result.state shouldBe LOOPING
        result.acc shouldBe 1394
    }
}


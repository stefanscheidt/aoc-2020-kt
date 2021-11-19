package day03

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import java.io.File
import java.nio.charset.StandardCharsets

class Day03Test {

    @Test
    fun `process sample 1`() {
        val world = """
            ..##.......
            #...#...#..
            .#....#..#.
            ..#.#...#.#
            .#...##..#.
            ..#.##.....
            .#.#.#....#
            .#........#
            #.##...#...
            #...##....#
            .#..#...#.#
        """.trimIndent()
        val result = processWorld1(world.lines())

        result shouldBe 7
    }

    @Test
    fun `process input 1`() {
        val result = File("./input/day03.txt")
            .useLines(StandardCharsets.UTF_8) { processWorld1(it.toList()) }

        result shouldBe 294
    }

    @Test
    fun `process sample 2`() {
        val world = """
            ..##.......
            #...#...#..
            .#....#..#.
            ..#.#...#.#
            .#...##..#.
            ..#.##.....
            .#.#.#....#
            .#........#
            #.##...#...
            #...##....#
            .#..#...#.#
        """.trimIndent()
        val result = processWorld2(world.lines())

        result shouldBe 336
    }

    @Test
    fun `process input 2`() {
        val result = File("./input/day03.txt")
            .useLines(StandardCharsets.UTF_8) { processWorld2(it.toList()) }

        result shouldBe 5774564250L
    }
}

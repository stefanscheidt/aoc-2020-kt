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
    fun `compute path`() {
        val path = path(5) // for a world with 6 rows
        path shouldBe listOf(Pos(0, 0), Pos(1, 3), Pos(2, 6), Pos(3, 9), Pos(4, 12), Pos(5, 15))
    }
}
package day07

import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import java.io.File

val sample = """
light red bags contain 1 bright white bag, 2 muted yellow bags.
dark orange bags contain 3 bright white bags, 4 muted yellow bags.
bright white bags contain 1 shiny gold bag.
muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.
shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.
dark olive bags contain 3 faded blue bags, 4 dotted black bags.
vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.
faded blue bags contain no other bags.
dotted black bags contain no other bags.            
""".trimIndent().lines()

class Day07Test {

    @Test
    fun `parse one rule`() {
        val input = "light red bags contain 1 bright white bag, 2 muted yellow bags."

        parseRule(input) shouldBe Rule(
            "light red bag",
            listOf(Content("bright white bag", 1), Content("muted yellow bag", 2))
        )
    }

    @Test
    fun `process sample 1`() {
        val ruleset = parseRules(sample)
            .associate { Pair(it.bag, it.content.map(Content::bag)) }

        allContainers("shiny gold bag", ruleset) shouldContainExactlyInAnyOrder listOf(
            "bright white bag",
            "muted yellow bag",
            "light red bag",
            "dark orange bag"
        )
    }

    @Test
    fun `process input 1`() {
        val input = File("./input/day07.txt").readLines().filter(String::isNotEmpty)

        val ruleset = parseRules(input)
            .associate { Pair(it.bag, it.content.map(Content::bag)) }

        allContainers("shiny gold bag", ruleset) shouldHaveSize 337
    }

    @Test
    fun `process sample 2`() {
        val ruleset = parseRules(sample)
            .associate { Pair(it.bag, it.content) }

        totalQuantityOf("shiny gold bag", ruleset) shouldBe 32
    }

    @Test
    fun `process sample 2, 2`() {
        val input = """
            shiny gold bags contain 2 dark red bags.
            dark red bags contain 2 dark orange bags.
            dark orange bags contain 2 dark yellow bags.
            dark yellow bags contain 2 dark green bags.
            dark green bags contain 2 dark blue bags.
            dark blue bags contain 2 dark violet bags.
            dark violet bags contain no other bags.
        """.trimIndent().lines()
        val ruleset = parseRules(input)
            .associate { Pair(it.bag, it.content) }

        totalQuantityOf("shiny gold bag", ruleset) shouldBe 126
    }

    @Test
    fun `process input 2`() {
        val input = File("./input/day07.txt").readLines().filter(String::isNotEmpty)

        val ruleset = parseRules(input)
            .associate { Pair(it.bag, it.content) }

        totalQuantityOf("shiny gold bag", ruleset) shouldBe 50100
    }
}

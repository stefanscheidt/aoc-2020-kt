package day07

import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import java.io.File

val sample1 = """
light red bags contain 1 bright white bag, 2 muted yellow bags.
dark orange bags contain 3 bright white bags, 4 muted yellow bags.
bright white bags contain 1 shiny gold bag.
muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.
shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.
dark olive bags contain 3 faded blue bags, 4 dotted black bags.
vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.
faded blue bags contain no other bags.
dotted black bags contain no other bags.            
""".trimIndent()

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
        val ruleset = parseRules(sample1)
            .associate { rule -> Pair(rule.bag, rule.content.map(Content::bag)) }

        allContainers(ruleset, "shiny gold bag") shouldContainExactlyInAnyOrder listOf(
            "bright white bag",
            "muted yellow bag",
            "light red bag",
            "dark orange bag"
        )
    }

    @Test
    fun `process input 1`() {
        val input = File("./input/day07.txt").readText()

        val ruleset = parseRules(input)
            .associate { rule -> Pair(rule.bag, rule.content.map(Content::bag)) }

        allContainers(ruleset, "shiny gold bag") shouldHaveSize 337
    }
}

typealias Bag = String

data class Content(val bag: Bag, val quantity: Int)

data class Rule(val bag: Bag, val content: List<Content>)

fun parseRule(input: String): Rule {
    val split = input.dropLast(1).replace("bags", "bag").split(" contain ")
    val bag = split[0]
    val content = split[1].split(", ")
        .mapNotNull { """^(\d+) ([a-zA-Z ]+)$""".toRegex().matchEntire(it) }
        .map {
            val (q, b) = it.destructured
            Content(b, q.toInt())
        }
    return Rule(bag, content)
}

fun parseRules(input: String): List<Rule> =
    input.lines().filter(String::isNotEmpty).map(::parseRule)

typealias Ruleset = Map<Bag, List<Bag>>

fun containers(ruleset: Ruleset, bag: Bag): Set<Bag> =
    ruleset.keys.filter { ruleset[it]?.contains(bag) ?: false }.toSet()

fun containers(ruleset: Ruleset, bags: Set<Bag>): Set<Bag> =
    bags.flatMap { bag -> containers(ruleset, bag) }.toSet()

fun allContainers(ruleset: Ruleset, bag: Bag): Set<Bag> {
    tailrec fun go(ruleset: Ruleset, acc: Pair<Set<Bag>, Set<Bag>>): Pair<Set<Bag>, Set<Bag>> {
        val (current, sum) = acc
        val next = containers(ruleset, current)
        return when {
            next.isEmpty() -> Pair(emptySet(), sum)
            else -> go(ruleset, Pair(next, sum + next))
        }
    }

    val init = Pair(setOf(bag), emptySet<Bag>())
    return go(ruleset, init).second
}

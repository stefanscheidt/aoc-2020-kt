package day07

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

fun parseRules(input: List<String>): List<Rule> =
    input.map(::parseRule)

// Part 1

typealias Ruleset1 = Map<Bag, List<Bag>>

fun containers(bag: Bag, ruleset: Ruleset1): Set<Bag> =
    ruleset.keys.filter { ruleset[it]?.contains(bag) ?: false }.toSet()

fun containers(bags: Set<Bag>, ruleset: Ruleset1): Set<Bag> =
    bags.flatMap { bag -> containers(bag, ruleset) }.toSet()

fun allContainers(bag: Bag, ruleset: Ruleset1): Set<Bag> {
    tailrec fun go(ruleset: Ruleset1, acc: Pair<Set<Bag>, Set<Bag>>): Pair<Set<Bag>, Set<Bag>> {
        val (current, sum) = acc
        val next = containers(current, ruleset)
        return when {
            next.isEmpty() -> Pair(emptySet(), sum)
            else -> go(ruleset, Pair(next, sum + next))
        }
    }

    val init = Pair(setOf(bag), emptySet<Bag>())
    return go(ruleset, init).second
}

// Part 2

typealias Ruleset2 = Map<Bag, List<Content>>

fun totalQuantityOf(bag: Bag, ruleset: Ruleset2): Int {
    return ruleset[bag]?.sumOf {
        it.quantity + it.quantity * totalQuantityOf(it.bag, ruleset)
    } ?: 0
}

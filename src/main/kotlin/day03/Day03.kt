package day03

fun processWorld1(world: World): Int =
    treeCount(world, Slope(1, 3))

fun processWorld2(world: World): Long =
    listOf(Slope(1, 1), Slope(1, 3), Slope(1, 5), Slope(1, 7), Slope(2, 1))
        .map { treeCount(world, it).toLong() }
        .fold(1) { acc, cur -> acc * cur }

private fun treeCount(world: World, slope: Slope): Int =
    slope.pathUpToX(world.count() - 1)
        .count { pos -> world.hasTreeAt(pos) }

typealias World = List<String>

fun World.hasTreeAt(pos: Pos): Boolean {
    val width = this[0].count()
    return this[pos.x][pos.y % width] == '#'
}

data class Pos(
    val x: Int,
    val y: Int
)

typealias Slope = Pos

fun Slope.pathUpToX(maxX: Int): List<Pos> =
    generateSequence(Pos(0, 0)) { pos -> pos + this }
        .takeWhile { pos -> pos.x <= maxX }
        .toList()

operator fun Pos.plus(slope: Slope): Pos =
    Pos(this.x + slope.x, this.y + slope.y)


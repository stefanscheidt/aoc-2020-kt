package day03

fun processWorld1(world: World): Int {
    val maxX = world.count() - 1
    val path = path(maxX)
    return path.filter { pos -> world.hasTreeAt(pos) }.count()
}

typealias World = List<String>

fun World.hasTreeAt(pos: Pos): Boolean {
    val width = this[0].count()
    return this[pos.x][pos.y % width] == '#'
}

data class Pos(
    val x: Int,
    val y: Int
)

fun path(maxX: Int): List<Pos> =
    generateSequence(Pos(0, 0)) { pos -> Pos(pos.x + 1, pos.y + 3) }
        .takeWhile { pos -> pos.x <= maxX }
        .toList()


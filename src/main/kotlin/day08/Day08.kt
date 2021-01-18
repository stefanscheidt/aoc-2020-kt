package day08

import day08.ComputerState.*

sealed class Instruction
data class Acc(val arg: Int) : Instruction()
data class Jmp(val arg: Int) : Instruction()
object Nop : Instruction()

typealias Program = List<Instruction>

data class Computer(
    val program: Program,
    val state: ComputerState = RUNNING,
    val ptr: Int = 0,
    val acc: Int = 0,
    val seen: Set<Int> = emptySet()
)

enum class ComputerState {
    HALTED, LOOPING, RUNNING
}

fun Computer.next(): Computer {
    if (ptr !in program.indices) return copy(state = HALTED)
    if (ptr in seen) return copy(state = LOOPING)

    return when (val instr = program[ptr]) {
        is Acc -> copy(acc = acc + instr.arg, ptr = ptr + 1, seen = seen + ptr)
        is Jmp -> copy(ptr = ptr + instr.arg, seen = seen + ptr)
        Nop -> copy(ptr = ptr + 1, seen = seen + ptr)
    }
}

fun parseProgramm(input: List<String>): Program =
    input.mapNotNull(::parseInstruction)

private fun parseInstruction(input: String): Instruction? {
    val (operation, argument) = """^(acc|jmp|nop) ([+\-]\d+)$""".toRegex()
        .matchEntire(input)?.destructured ?: return null
    return when (operation) {
        "acc" -> Acc(argument.toInt())
        "jmp" -> Jmp(argument.toInt())
        "nop" -> Nop
        else -> null
    }
}

fun runProgramm(program: Program): Computer =
    generateSequence(Computer(program), Computer::next)
        .dropWhile { it.state == RUNNING }
        .first()

package day08

import day08.ComputerState.HALTED
import day08.ComputerState.LOOPING
import day08.ComputerState.RUNNING

sealed class Instruction
data class Acc(val arg: Int) : Instruction()
data class Jmp(val arg: Int) : Instruction()
data class Nop(val arg: Int) : Instruction()

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
        is Nop -> copy(ptr = ptr + 1, seen = seen + ptr)
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
        "nop" -> Nop(argument.toInt())
        else -> null
    }
}

// Part 1

fun runProgramm(prog: Program): Computer =
    generateSequence(Computer(prog), Computer::next)
        .dropWhile { it.state == RUNNING }
        .first()

// Part 2

private fun Instruction.flipped(): Instruction =
    when (this) {
        is Acc -> this
        is Jmp -> Nop(arg)
        is Nop -> Jmp(arg)
    }

private fun Program.flippedAt(index: Int): Program =
    mapIndexed { idx, instr ->
        if (idx == index) instr.flipped() else instr
    }

fun fixProgramm(prog: Program): Computer =
    (0..prog.size).asSequence()
        .mapNotNull { idx ->
            if (prog[idx].flipped() == prog[idx]) null
            else runProgramm(prog.flippedAt(idx))
        }
        .dropWhile { it.state != HALTED }
        .first()

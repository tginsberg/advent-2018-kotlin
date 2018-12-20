/*
 * Copyright (c) 2018 by Todd Ginsberg
 */

/**
 * Advent of Code 2018, Day 19 - Chronal Classification
 *
 * Problem Description: http://adventofcode.com/2018/day/19
 * Blog Post/Commentary: https://todd.ginsberg.com/post/advent-of-code/2018/day19/
 */
package com.ginsberg.advent2018


class Day19(rawInput: List<String>) {

    private val instructionPointer: Int = rawInput.first().split(" ")[1].toInt()
    private val instructions: List<Instruction> = Instruction.of(rawInput.drop(1))

    fun solvePart1(): Int =
        execute(instructions, instructionPointer).first()

    fun solvePart2(): Int =
        10551288.factors().sum()

    private fun execute(instructions: List<Instruction>, ipBind: Int): Registers {
        var registers = IntArray(6)
        var ip = registers[ipBind]
        while (ip in (0 until instructions.size)) {
            registers[ipBind] = ip
            val thisInstruction = instructions[ip]
            registers = Operations.operations.getValue(thisInstruction.name).invoke(registers, thisInstruction)
            ip = registers[ipBind] + 1
        }
        return registers
    }

    private fun Int.factors(): List<Int> =
        (1..this).mapNotNull { n ->
            if (this % n == 0) n else null
        }

    private data class Instruction(val name: String, val a: Int, val b: Int, val c: Int) {
        companion object {
            fun of(input: List<String>): List<Instruction> =
                input.map {
                    val (op, a, b, c) = it.split(" ")
                    Instruction(op, a.toInt(), b.toInt(), c.toInt())
                }
        }
    }

    private object Operations {

        val operations: Map<String, (Registers, Instruction) -> Registers> = mapOf(
            "addr" to ::addr,
            "addi" to ::addi,
            "mulr" to ::mulr,
            "muli" to ::muli,
            "banr" to ::banr,
            "bani" to ::bani,
            "borr" to ::borr,
            "bori" to ::bori,
            "setr" to ::setr,
            "seti" to ::seti,
            "gtir" to ::gtir,
            "gtri" to ::gtri,
            "gtrr" to ::gtrr,
            "eqir" to ::eqir,
            "eqri" to ::eqri,
            "eqrr" to ::eqrr
        )

        private fun addr(registers: Registers, instruction: Instruction): Registers =
            registers.copyOf().apply { this[instruction.c] = registers[instruction.a] + registers[instruction.b] }

        private fun addi(registers: Registers, instruction: Instruction): Registers =
            registers.copyOf().apply { this[instruction.c] = registers[instruction.a] + instruction.b }

        private fun mulr(registers: Registers, instruction: Instruction): Registers =
            registers.copyOf().apply { this[instruction.c] = registers[instruction.a] * registers[instruction.b] }

        private fun muli(registers: Registers, instruction: Instruction): Registers =
            registers.copyOf().apply { this[instruction.c] = registers[instruction.a] * instruction.b }

        private fun banr(registers: Registers, instruction: Instruction): Registers =
            registers.copyOf().apply { this[instruction.c] = registers[instruction.a] and registers[instruction.b] }

        private fun bani(registers: Registers, instruction: Instruction): Registers =
            registers.copyOf().apply { this[instruction.c] = registers[instruction.a] and instruction.b }

        private fun borr(registers: Registers, instruction: Instruction): Registers =
            registers.copyOf().apply { this[instruction.c] = registers[instruction.a] or registers[instruction.b] }

        private fun bori(registers: Registers, instruction: Instruction): Registers =
            registers.copyOf().apply { this[instruction.c] = registers[instruction.a] or instruction.b }

        private fun setr(registers: Registers, instruction: Instruction): Registers =
            registers.copyOf().apply { this[instruction.c] = registers[instruction.a] }

        private fun seti(registers: Registers, instruction: Instruction): Registers =
            registers.copyOf().apply { this[instruction.c] = instruction.a }

        private fun gtir(registers: Registers, instruction: Instruction): Registers =
            registers.copyOf().apply { this[instruction.c] = if (instruction.a > registers[instruction.b]) 1 else 0 }

        private fun gtri(registers: Registers, instruction: Instruction): Registers =
            registers.copyOf().apply { this[instruction.c] = if (registers[instruction.a] > instruction.b) 1 else 0 }

        private fun gtrr(registers: Registers, instruction: Instruction): Registers =
            registers.copyOf().apply { this[instruction.c] = if (registers[instruction.a] > registers[instruction.b]) 1 else 0 }

        private fun eqir(registers: Registers, instruction: Instruction): Registers =
            registers.copyOf().apply { this[instruction.c] = if (instruction.a == registers[instruction.b]) 1 else 0 }

        private fun eqri(registers: Registers, instruction: Instruction): Registers =
            registers.copyOf().apply { this[instruction.c] = if (registers[instruction.a] == instruction.b) 1 else 0 }

        private fun eqrr(registers: Registers, instruction: Instruction): Registers =
            registers.copyOf().apply { this[instruction.c] = if (registers[instruction.a] == registers[instruction.b]) 1 else 0 }
    }
}

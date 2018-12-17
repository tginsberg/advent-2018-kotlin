/*
 * Copyright (c) 2018 by Todd Ginsberg
 */

/**
 * Advent of Code 2018, Day 16 - Chronal Classification
 *
 * Problem Description: http://adventofcode.com/2018/day/16
 * Blog Post/Commentary: https://todd.ginsberg.com/post/advent-of-code/2018/day16/
 */
package com.ginsberg.advent2018

typealias Registers = IntArray
typealias Instruction = IntArray
typealias Operation = (Registers, Instruction) -> Registers

class Day16(
    part1RawInput: List<String>,
    part2RawInput: List<String>
) {

    private val part1Input: List<Input> = parsePart1Input(part1RawInput)
    private val part2Input: List<Instruction> = parsePart2Input(part2RawInput)

    fun solvePart1(): Int =
        part1Input.count { countMatchingOperations(it) >= 3 }

    fun solvePart2(): Int {
        // Create all possible matches.
        val functionToOpCodes: MutableMap<Operation, MutableSet<Int>> = part1Input.flatMap { input ->
            Operations.operations.mapNotNull { operation ->
                if (operation(input.registersBefore, input.instruction).contentEquals(input.expectedRegisters)) {
                    input.id to operation
                } else {
                    null
                }
            }
        }
            .groupBy({ it.second }, { it.first })
            .mapValues { (_, list) -> list.toMutableSet() }
            .toMutableMap()

        val operations = mutableMapOf<Int, Operation>()
        while (functionToOpCodes.isNotEmpty()) {
            // Find all that have only one outcome, map them into the operations map and remove them from contention,
            functionToOpCodes
                .filter { (_, codes) -> codes.size == 1 }
                .map { Pair(it.key, it.value.first()) }
                .forEach { (op, code) ->
                    operations[code] = op
                    functionToOpCodes.remove(op)
                    functionToOpCodes.forEach { (_, thoseFuncs) -> thoseFuncs.remove(code) }
                }
            functionToOpCodes.entries.removeIf { (_, value) -> value.isEmpty() }
        }


        // Run the code and return register 0
        return part2Input.fold(intArrayOf(0, 0, 0, 0)) { registers, instruction ->
            operations[instruction[0]]!!.invoke(registers, instruction)
        }.first()
    }

    private fun countMatchingOperations(input: Input): Int =
        Operations.operations.count { it(input.registersBefore, input.instruction).contentEquals(input.expectedRegisters) }


    private companion object {
        val digitsRegex = """[^0-9 ]""".toRegex()

        fun parsePart1Input(rawInput: List<String>): List<Input> =
            rawInput.chunked(4) { chunk ->
                Input(
                    chunk[0].toIntArray(),
                    chunk[1].toIntArray(),
                    chunk[2].toIntArray()
                )
            }

        fun parsePart2Input(rawInput: List<String>): List<Instruction> =
            rawInput.map { it.toIntArray() }

        private fun String.toIntArray(): IntArray =
            this.replace(digitsRegex, "").trim().split(" ").map { it.toInt() }.toIntArray()

    }

    private class Input(val registersBefore: Registers, val instruction: Instruction, val expectedRegisters: Registers) {
        val id = instruction[0]
    }

    private object Operations {
        val operations: List<Operation> = listOf(
            ::addr,
            ::addi,
            ::mulr,
            ::muli,
            ::banr,
            ::bani,
            ::borr,
            ::bori,
            ::setr,
            ::seti,
            ::gtir,
            ::gtri,
            ::gtrr,
            ::eqir,
            ::eqri,
            ::eqrr
        )

        fun addr(registers: Registers, instruction: Instruction): Registers =
            registers.copyOf().apply { this[instruction[3]] = registers[instruction[1]] + registers[instruction[2]] }

        fun addi(registers: Registers, instruction: Instruction): Registers =
            registers.copyOf().apply { this[instruction[3]] = registers[instruction[1]] + instruction[2] }

        fun mulr(registers: Registers, instruction: Instruction): Registers =
            registers.copyOf().apply { this[instruction[3]] = registers[instruction[1]] * registers[instruction[2]] }

        fun muli(registers: Registers, instruction: Instruction): Registers =
            registers.copyOf().apply { this[instruction[3]] = registers[instruction[1]] * instruction[2] }

        fun banr(registers: Registers, instruction: Instruction): Registers =
            registers.copyOf().apply { this[instruction[3]] = registers[instruction[1]] and registers[instruction[2]] }

        fun bani(registers: Registers, instruction: Instruction): Registers =
            registers.copyOf().apply { this[instruction[3]] = registers[instruction[1]] and instruction[2] }

        fun borr(registers: Registers, instruction: Instruction): Registers =
            registers.copyOf().apply { this[instruction[3]] = registers[instruction[1]] or registers[instruction[2]] }

        fun bori(registers: Registers, instruction: Instruction): Registers =
            registers.copyOf().apply { this[instruction[3]] = registers[instruction[1]] or instruction[2] }

        fun setr(registers: Registers, instruction: Instruction): Registers =
            registers.copyOf().apply { this[instruction[3]] = registers[instruction[1]] }

        fun seti(registers: Registers, instruction: Instruction): Registers =
            registers.copyOf().apply { this[instruction[3]] = instruction[1] }

        fun gtir(registers: Registers, instruction: Instruction): Registers =
            registers.copyOf().apply { this[instruction[3]] = if (instruction[1] > registers[instruction[2]]) 1 else 0 }

        fun gtri(registers: Registers, instruction: Instruction): Registers =
            registers.copyOf().apply { this[instruction[3]] = if (registers[instruction[1]] > instruction[2]) 1 else 0 }

        fun gtrr(registers: Registers, instruction: Instruction): Registers =
            registers.copyOf().apply { this[instruction[3]] = if (registers[instruction[1]] > registers[instruction[2]]) 1 else 0 }

        fun eqir(registers: Registers, instruction: Instruction): Registers =
            registers.copyOf().apply { this[instruction[3]] = if (instruction[1] == registers[instruction[2]]) 1 else 0 }

        fun eqri(registers: Registers, instruction: Instruction): Registers =
            registers.copyOf().apply { this[instruction[3]] = if (registers[instruction[1]] == instruction[2]) 1 else 0 }

        fun eqrr(registers: Registers, instruction: Instruction): Registers =
            registers.copyOf().apply { this[instruction[3]] = if (registers[instruction[1]] == registers[instruction[2]]) 1 else 0 }
    }
}

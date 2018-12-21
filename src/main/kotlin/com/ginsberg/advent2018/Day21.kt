/*
 * Copyright (c) 2018 by Todd Ginsberg
 */

/**
 * Advent of Code 2018, Day 21 - Chronal Conversion
 *
 * Problem Description: http://adventofcode.com/2018/day/21
 * Blog Post/Commentary: https://todd.ginsberg.com/post/advent-of-code/2018/day21/
 */
package com.ginsberg.advent2018


class Day21(rawInput: List<String>, private val magicRegister: Int = 4, private val magicInstruction: Int = 28) {

    private val instructionPointer: Int = rawInput.first().split(" ")[1].toInt()
    private val instructions: List<ElfCodeInstruction> = ElfCodeInstruction.of(rawInput.drop(1))

    fun solvePart1(): Int =
        execute(instructions, instructionPointer).first()

    fun solvePart2(): Int =
        execute(instructions, instructionPointer).last()

    private fun execute(instructions: List<ElfCodeInstruction>, ipBind: Int): Sequence<Int> = sequence {
        var registers = IntArray(6)
        var ip = registers[ipBind]
        val seen = LinkedHashSet<Int>()
        while (ip in (0 until instructions.size)) {
            registers[ipBind] = ip
            val thisInstruction = instructions[ip]
            registers = ElfCodeOperations[thisInstruction.name].invoke(registers, thisInstruction)
            ip = registers[ipBind] + 1
            if(ip == magicInstruction) {
                if(registers[magicRegister] in seen) {
                    yield(seen.last())
                    return@sequence
                }
                seen += registers[magicRegister]
                yield(registers[magicRegister])
            }
        }
    }
}

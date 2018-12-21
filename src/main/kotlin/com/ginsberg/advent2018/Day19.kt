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
    private val instructions: List<ElfCodeInstruction> = ElfCodeInstruction.of(rawInput.drop(1))

    fun solvePart1(): Int =
        execute(instructions, instructionPointer).first()

    fun solvePart2(): Int =
        10551288.factors().sum()

    private fun execute(instructions: List<ElfCodeInstruction>, ipBind: Int): Registers {
        var registers = IntArray(6)
        var ip = registers[ipBind]
        while (ip in (0 until instructions.size)) {
            registers[ipBind] = ip
            val thisInstruction = instructions[ip]
            registers = ElfCodeOperations[thisInstruction.name].invoke(registers, thisInstruction)
            ip = registers[ipBind] + 1
        }
        return registers
    }

    private fun Int.factors(): List<Int> =
        (1..this).mapNotNull { n ->
            if (this % n == 0) n else null
        }

}


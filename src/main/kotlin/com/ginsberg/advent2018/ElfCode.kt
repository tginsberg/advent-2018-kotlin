/*
 * Copyright (c) 2018 by Todd Ginsberg
 */

package com.ginsberg.advent2018

data class ElfCodeInstruction(val name: String, val a: Int, val b: Int, val c: Int) {
    companion object {
        fun of(input: List<String>): List<ElfCodeInstruction> =
            input.map {
                val (op, a, b, c) = it.split(" ")
                ElfCodeInstruction(op, a.toInt(), b.toInt(), c.toInt())
            }
    }
}

object ElfCodeOperations {

    private val operations: Map<String, (Registers, ElfCodeInstruction) -> Registers> = mapOf(
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

    operator fun get(key: String): (Registers, ElfCodeInstruction) -> Registers =
        operations[key]!!

    private fun addr(registers: Registers, instruction: ElfCodeInstruction): Registers =
        registers.copyOf().apply { this[instruction.c] = registers[instruction.a] + registers[instruction.b] }

    private fun addi(registers: Registers, instruction: ElfCodeInstruction): Registers =
        registers.copyOf().apply { this[instruction.c] = registers[instruction.a] + instruction.b }

    private fun mulr(registers: Registers, instruction: ElfCodeInstruction): Registers =
        registers.copyOf().apply { this[instruction.c] = registers[instruction.a] * registers[instruction.b] }

    private fun muli(registers: Registers, instruction: ElfCodeInstruction): Registers =
        registers.copyOf().apply { this[instruction.c] = registers[instruction.a] * instruction.b }

    private fun banr(registers: Registers, instruction: ElfCodeInstruction): Registers =
        registers.copyOf().apply { this[instruction.c] = registers[instruction.a] and registers[instruction.b] }

    private fun bani(registers: Registers, instruction: ElfCodeInstruction): Registers =
        registers.copyOf().apply { this[instruction.c] = registers[instruction.a] and instruction.b }

    private fun borr(registers: Registers, instruction: ElfCodeInstruction): Registers =
        registers.copyOf().apply { this[instruction.c] = registers[instruction.a] or registers[instruction.b] }

    private fun bori(registers: Registers, instruction: ElfCodeInstruction): Registers =
        registers.copyOf().apply { this[instruction.c] = registers[instruction.a] or instruction.b }

    private fun setr(registers: Registers, instruction: ElfCodeInstruction): Registers =
        registers.copyOf().apply { this[instruction.c] = registers[instruction.a] }

    private fun seti(registers: Registers, instruction: ElfCodeInstruction): Registers =
        registers.copyOf().apply { this[instruction.c] = instruction.a }

    private fun gtir(registers: Registers, instruction: ElfCodeInstruction): Registers =
        registers.copyOf().apply { this[instruction.c] = if (instruction.a > registers[instruction.b]) 1 else 0 }

    private fun gtri(registers: Registers, instruction: ElfCodeInstruction): Registers =
        registers.copyOf().apply { this[instruction.c] = if (registers[instruction.a] > instruction.b) 1 else 0 }

    private fun gtrr(registers: Registers, instruction: ElfCodeInstruction): Registers =
        registers.copyOf().apply { this[instruction.c] = if (registers[instruction.a] > registers[instruction.b]) 1 else 0 }

    private fun eqir(registers: Registers, instruction: ElfCodeInstruction): Registers =
        registers.copyOf().apply { this[instruction.c] = if (instruction.a == registers[instruction.b]) 1 else 0 }

    private fun eqri(registers: Registers, instruction: ElfCodeInstruction): Registers =
        registers.copyOf().apply { this[instruction.c] = if (registers[instruction.a] == instruction.b) 1 else 0 }

    private fun eqrr(registers: Registers, instruction: ElfCodeInstruction): Registers =
        registers.copyOf().apply { this[instruction.c] = if (registers[instruction.a] == registers[instruction.b]) 1 else 0 }
}

